package com.quan.security.common.util;

import com.alibaba.fastjson.JSON;
import com.quan.tools.redis.service.IRedisService;
import com.quan.security.common.AudienceEnum;
import com.quan.security.common.CacheConstants;
import com.quan.security.common.config.TokenConfiguration;
import com.quan.security.common.dto.entity.AuthEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author wangquan
 */
@EnableConfigurationProperties(TokenConfiguration.class)
@Component
public class AuthUtil {

    private static IRedisService redisService;

    private static String issuer;
    private static String key;
    private static String subject;
    public static String claimsName;

    public AuthUtil(IRedisService redisService, TokenConfiguration tokenConfiguration) {
        AuthUtil.redisService = redisService;
        AuthUtil.issuer = tokenConfiguration.getIssuer();
        AuthUtil.key = tokenConfiguration.getKey();
        AuthUtil.subject = tokenConfiguration.getSubject();
        AuthUtil.claimsName = tokenConfiguration.getClaimsName();
    }

    public static AuthEntity getAuthEntity(String token) {
        // 获取jwt中的信息
        Claims claims = getClaims(token);
        String audience = claims != null ? claims.getAudience() : "";

        // 认证用户 从缓存拿数据 如果拿不到 则重新登录
        if (AudienceEnum.AUTH_USER.eq(audience)) {

            // 获取token中的信息
            String auth = redisService.get(CacheConstants.TOKEN_PREFIX + token);

            // redis中没有对应信息
            if (StringUtils.isEmpty(auth)) {
                return null;
            }

            // 转换user 并设置返回信息
            AuthEntity authEntity = JSON.parseObject(auth, AuthEntity.class);
            return authEntity;
        }
        return null;
    }

    /**
     * @return
     */
    private static Date getOneMonthLater() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }

    /**
     * 验证token
     *
     * @param jwt
     * @return
     */
    public static Claims getClaims(String jwt) {

        Claims claims = null;
        if (StringUtils.isEmpty(jwt)) {
            return null;
        }

        try {
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            // 非法的jwt或者jwt过期等等
        }

        return claims;
    }

    /**
     * 生成token
     *
     * @param aud the JWT aud value or null to remove the property from the Claims map.
     * @return
     */
    public static String generateToken(String aud) {
        return generateToken(aud, UUID.randomUUID().toString().replace("-", ""));
    }

    /**
     * @param aud   the JWT aud value or null to remove the property from the Claims map.
     * @param value the value to set for the specified Claims property name
     * @return
     */
    public static String generateToken(String aud, Object value) {
        // 生成token
        String token = Jwts.builder().claim(claimsName, value).setAudience(aud).setSubject(subject).setIssuer(issuer).setIssuedAt(Calendar.getInstance().getTime()).setExpiration(getOneMonthLater()).signWith(SignatureAlgorithm.HS512, key).compact();
        return token;
    }

}
