package cn.javaquan.security.common.util;

import cn.javaquan.security.common.config.TokenConfiguration;
import com.alibaba.fastjson.JSON;
import cn.javaquan.tools.redis.service.IRedisService;
import cn.javaquan.security.common.AudienceEnum;
import cn.javaquan.security.common.CacheConstants;
import cn.javaquan.security.common.dto.entity.AuthEntity;
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
 * 认证工具类.
 *
 * @author wangquan
 * @since 1.0.0
 */
@EnableConfigurationProperties(TokenConfiguration.class)
@Component
public class AuthUtil {

    private static IRedisService redisService;

    /**
     * issuer. {@link TokenConfiguration#issuer}
     */
    private static String issuer;

    /**
     * key. {@link TokenConfiguration#key}
     */
    private static String key;

    /**
     * subject. {@link TokenConfiguration#subject}
     */
    private static String subject;

    /**
     * claimsName. {@link TokenConfiguration#claimsName}
     */
    public static String claimsName;

    /**
     * 构造方法，注入静态的实例.
     * @param redisService redisService
     * @param tokenConfiguration token配置
     */
    public AuthUtil(IRedisService redisService, TokenConfiguration tokenConfiguration) {
        AuthUtil.redisService = redisService;
        AuthUtil.issuer = tokenConfiguration.getIssuer();
        AuthUtil.key = tokenConfiguration.getKey();
        AuthUtil.subject = tokenConfiguration.getSubject();
        AuthUtil.claimsName = tokenConfiguration.getClaimsName();
    }

    /**
     * 获取认证用户信息.
     * <p>
     * 根据token获取用户信息
     * @param token the JWT value.
     * @return 用户认证信息
     */
    public static AuthEntity getAuthEntity(String token) {
        // 获取jwt中的信息
        Claims claims = getClaims(token);
        String audience = (claims != null) ? claims.getAudience() : "";

        // 认证用户 从缓存拿数据 如果拿不到 则重新登录
        if (AudienceEnum.AUTH_USER.eq(audience)) {

            // 获取token中的信息
            String auth = redisService.get(CacheConstants.TOKEN_PREFIX + token);

            // redis中没有对应信息
            if (!StringUtils.hasText(auth)) {
                return null;
            }

            // 转换user 并设置返回信息
            AuthEntity authEntity = JSON.parseObject(auth, AuthEntity.class);
            return authEntity;
        }
        return null;
    }

    /**
     * 获取一个月后时间.
     * @return 以当前时间为基点，获取的一个月后的时间
     */
    private static Date getOneMonthLater() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }

    /**
     * 验证token.
     * @param jwt the JWT value.
     * @return the Claims if the JWT value is valid or null if it isn't.
     */
    public static Claims getClaims(String jwt) {

        Claims claims = null;
        if (!StringUtils.hasText(jwt)) {
            return null;
        }

        try {
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        }
        catch (Exception ex) {
            // 非法的jwt或者jwt过期等等
        }

        return claims;
    }

    /**
     * 生成token.
     * @param aud the JWT aud value or null to remove the property from the Claims map.
     * @return token
     */
    public static String generateToken(String aud) {
        return generateToken(aud, UUID.randomUUID().toString().replace("-", ""));
    }

    /**
     * 生成token.
     * @param aud the JWT aud value or null to remove the property from the Claims map.
     * @param value the value to set for the specified Claims property name
     * @return token
     */
    public static String generateToken(String aud, Object value) {
        // 生成token
        String token = Jwts.builder()
            .claim(claimsName, value)
            .setAudience(aud)
            .setSubject(subject)
            .setIssuer(issuer)
            .setIssuedAt(Calendar.getInstance().getTime())
            .setExpiration(getOneMonthLater())
            .signWith(SignatureAlgorithm.HS512, key)
            .compact();
        return token;
    }

}
