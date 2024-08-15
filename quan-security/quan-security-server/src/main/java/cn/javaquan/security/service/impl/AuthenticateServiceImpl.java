package cn.javaquan.security.service.impl;

import com.alibaba.fastjson.JSON;
import cn.javaquan.tools.redis.service.IRedisService;
import cn.javaquan.security.common.AccessorTypeEnum;
import cn.javaquan.security.common.AudienceEnum;
import cn.javaquan.security.common.CacheConstants;
import cn.javaquan.security.common.dto.AccessorInfo;
import cn.javaquan.security.common.dto.entity.AuthEntity;
import cn.javaquan.security.common.dto.request.AuthenticateRequest;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;
import cn.javaquan.security.common.util.AuthUtil;
import cn.javaquan.security.service.AuthenticateService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 权限认证服务实现.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final IRedisService redisService;

    @Override
    public AuthenticateResponse authenticate(AuthenticateRequest request) {

        // 定义返回对象
        AuthenticateResponse response = new AuthenticateResponse();
        response.setExecute(false);

        AccessorInfo info = new AccessorInfo();
        info.setType(AccessorTypeEnum.NON_ANON.getType());
        response.setInfo(info);

        // 获取jwt中的信息
        Claims claims = AuthUtil.getClaims(request.getToken());
        String audience = (claims != null) ? claims.getAudience() : "";

        // 认证用户 从缓存拿数据 如果拿不到 则重新登录
        if (AudienceEnum.AUTH_USER.eq(audience)) {

            // 获取token中的信息
            String auth = redisService.get(CacheConstants.TOKEN_PREFIX + request.getToken());

            // redis中没有对应信息
            if (!StringUtils.hasText(auth)) {
                return response;
            }

            // 转换user 并设置返回信息
            AuthEntity authEntity = JSON.parseObject(auth, AuthEntity.class);
            response.setExecute(true);

            info.setAuthId(authEntity.getAuthId());
            info.setRoles(authEntity.getRoles());
            info.setData(authEntity.getData());

            return response;
        }

        // 用户身份 已处理完毕，处理非用户
        info.setType(AccessorTypeEnum.ANON.getType());

        // 游客访问需要登录的接口
        if (AccessorTypeEnum.AUTHENTICATED.eq(request.getType())) {
            return response;
        }

        String identity;
        if (StringUtils.hasText(audience)) {
            identity = (String) claims.get(AuthUtil.claimsName);
        }
        else {
            identity = UUID.randomUUID().toString().replace("-", "");
            String token = AuthUtil.generateToken(AudienceEnum.ANON_USER.getType(), identity);
            response.setToken(token);
        }

        info.setAuthId(identity);
        response.setExecute(true);

        return response;
    }

    @Override
    public String getToken(AuthEntity authEntity) {
        // 生成token
        String token = AuthUtil.generateToken(AudienceEnum.AUTH_USER.getType());

        // 获取缓存token的key
        String tokenKeyCache = CacheConstants.USER_TOKEN_CURRENT + authEntity.getAuthId();
        // 获取缓存的token
        String tokenCache = redisService.get(tokenKeyCache);

        // 清除缓存的token
        if (tokenCache != null) {
            String cleanCacheTokenKey = CacheConstants.TOKEN_PREFIX + tokenCache;
            redisService.del(cleanCacheTokenKey);
        }

        // 缓存token
        String tokenKey = CacheConstants.TOKEN_PREFIX + token;
        // 保存到redis，并设置一个月到期时间
        redisService.set(tokenKey, JSON.toJSONString(authEntity), 2592000);

        // 保存用户的唯一token
        redisService.set(tokenKeyCache, token, 2592000);
        return token;
    }

    @Override
    public void cleanToken(String token) {
        if (!StringUtils.hasText(token)) {
            return;
        }
        // 无用户信息
        Claims claims = AuthUtil.getClaims(token);
        if (claims == null) {
            return;
        }

        // 游客身份
        if (AudienceEnum.ANON_USER.eq(claims.getAudience())) {
            return;
        }

        // 获取token中的信息
        String userStr = redisService.get(CacheConstants.TOKEN_PREFIX + token);

        // 当前没有登录信息
        if (!StringUtils.hasText(userStr)) {
            return;
        }

        // 转换用户
        AuthEntity authEntity = JSON.parseObject(userStr, AuthEntity.class);

        // 删除缓存中的登录信息
        String userToken = CacheConstants.USER_TOKEN_CURRENT + authEntity.getAuthId();
        String preTokenKey = CacheConstants.TOKEN_PREFIX + token;
        redisService.del(userToken, preTokenKey);
    }

}
