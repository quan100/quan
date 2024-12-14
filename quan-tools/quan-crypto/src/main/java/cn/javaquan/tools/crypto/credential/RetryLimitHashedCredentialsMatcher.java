package cn.javaquan.tools.crypto.credential;

import cn.javaquan.tools.crypto.CryptoParam;
import cn.javaquan.tools.crypto.CryptoProperties;
import cn.javaquan.tools.redis.service.IRedisService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义凭证校验.
 *
 * @author javaquan
 * @since 2.3.2
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private final IRedisService redisService;

    private final CryptoProperties properties;

    /**
     * 登录ip.
     */
    private String ip;

    /**
     * 自定义凭证校验默认构造器.
     * @param redisService redis服务
     * @param properties 密码配置
     */
    public RetryLimitHashedCredentialsMatcher(IRedisService redisService, CryptoProperties properties) {
        this.redisService = redisService;
        this.properties = properties;
    }

    /**
     * 获取登录ip.
     * @return 登录ip
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * 设置登录ip.
     * @param ip 登录ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String account = (String) token.getPrincipal();
        this.setHashAlgorithmName(this.properties.determineDefaultAlgorithmName());
        // 设置迭代次数
        this.setHashIterations(this.properties.determineDefaultHashIterations());

        String cacheKey = cacheKey(account);

        AtomicInteger atomicInteger = this.redisService.get(cacheKey, AtomicInteger.class);
        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger(0);
            redisService.set(cacheKey, atomicInteger, this.properties.determineDefaultTimeOut());
        }

        // retry count + 1
        int cacheRetryCount = atomicInteger.incrementAndGet();
        if (cacheRetryCount > this.properties.determineDefaultRetryCount()) {
            // if retry count > 5 throw
            throw new ExcessiveAttemptsException("您的操作过于频繁，已被系统锁定，请联系管理员或稍后再试！");
        }

        boolean matches = super.doCredentialsMatch(token, info);

        if (matches) {
            // clear retry count
            this.redisService.del(cacheKey);
        }
        else {
            this.redisService.set(cacheKey, atomicInteger, cacheRetryCount * this.properties.determineDefaultTimeOut());
        }

        return matches;
    }

    /**
     * 凭证校验.
     * @param cryptoParam 密码参数
     * @return 校验是否通过
     */
    public boolean verify(CryptoParam cryptoParam) {
        // 创建认证信息和令牌
        UsernamePasswordToken token = new UsernamePasswordToken(cryptoParam.getAccount(), cryptoParam.getPassword());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(cryptoParam.getAccount(), cryptoParam.getSecret(),
                getByteSource(cryptoParam), properties.determineDefaultRealmName());
        setIp(cryptoParam.getIp());
        return this.doCredentialsMatch(token, info);
    }

    /**
     * 获取ByteSource.
     * @param cryptoParam 密码参数
     * @return byteSource
     */
    private ByteSource getByteSource(CryptoParam cryptoParam) {
        return ByteSource.Util.bytes(cryptoParam.getAccount() + cryptoParam.getSalt());
    }

    /**
     * 缓存键值.
     * @param suffix 缓存键的后缀
     * @return 缓存键
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
