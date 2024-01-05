package cn.javaquan.tools.crypto.credential;

import cn.javaquan.tools.crypto.CryptoParam;
import cn.javaquan.tools.crypto.CryptoProperties;
import cn.javaquan.tools.redis.service.IRedisService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 自定义凭证校验
 *
 * @author javaquan
 * @since 2.2.0
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private final IRedisService redisService;
    private final CryptoProperties properties;

    /**
     * 登录ip
     */
    private String ip;

    public RetryLimitHashedCredentialsMatcher(IRedisService redisService, CryptoProperties properties) {
        this.redisService = redisService;
        this.properties = properties;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String account = (String) token.getPrincipal();
        this.setHashAlgorithmName(properties.determineDefaultAlgorithmName());
        // 设置迭代次数
        this.setHashIterations(properties.determineDefaultHashIterations());

        String cacheKey = cacheKey(account);

        AtomicInteger atomicInteger = redisService.get(cacheKey, AtomicInteger.class);
        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger(0);
            redisService.set(cacheKey, atomicInteger, properties.determineDefaultTimeOut());
        }

        // retry count + 1
        int cacheRetryCount = atomicInteger.incrementAndGet();
        if (cacheRetryCount > properties.determineDefaultRetryCount()) {
            // if retry count > 5 throw
            throw new ExcessiveAttemptsException("您的操作过于频繁，已被系统锁定，请联系管理员或稍后再试！");
        }

        boolean matches = super.doCredentialsMatch(token, info);

        if (matches) {
            // clear retry count
            redisService.del(cacheKey);
        } else {
            redisService.set(cacheKey, atomicInteger, cacheRetryCount * properties.determineDefaultTimeOut());
        }

        return matches;
    }

    /**
     * 凭证校验
     *
     * @param cryptoParam
     * @return
     */
    public boolean verify(CryptoParam cryptoParam) {
        // 创建认证信息和令牌
        UsernamePasswordToken token = new UsernamePasswordToken(cryptoParam.getAccount(), cryptoParam.getPassword());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(cryptoParam.getAccount(), cryptoParam.getSecret(), getByteSource(cryptoParam), cryptoParam.getAccount());
        setIp(cryptoParam.getIp());
        return this.doCredentialsMatch(token, info);
    }

    private ByteSource getByteSource(CryptoParam cryptoParam) {
        return ByteSource.Util.bytes(cryptoParam.getAccount() + cryptoParam.getSalt());
    }

    /**
     * 缓存键值
     *
     * @param suffix
     * @return
     */
    private String cacheKey(String suffix) {
        StringBuffer sb = new StringBuffer();
        sb.append("login:");
        sb.append(getIp());
        sb.append(":");
        sb.append(suffix);
        return sb.toString();
    }
}
