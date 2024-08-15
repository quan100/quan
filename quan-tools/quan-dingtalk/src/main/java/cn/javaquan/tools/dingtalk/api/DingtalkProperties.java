package cn.javaquan.tools.dingtalk.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

/**
 * Configuration properties for dingtalk support.
 *
 * @author javaquan
 * @since 2.2.0
 */
@ConfigurationProperties(prefix = "alibaba.dingtalk")
public class DingtalkProperties {

    /**
     * 保存授权码的有效期.
     * <p>
     * 默认的过期时间，单位：秒。
     */
    private static final Long DEFAULT_EXPIRE_IN = 2592000L;

    /**
     * app key.
     */
    private String appKey;

    /**
     * app secret.
     */
    private String appSecret;

    /**
     * 有效期.
     */
    private Long expireIn;

    /**
     * 获取默认的appKey.
     * @return appKey
     */
    public String getAppKey() {
        return this.appKey;
    }

    /**
     * 设置默认的appKey.
     * @param appKey appKey
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * 确定默认的appKey.
     * @return appKey
     */
    public String determineDefaultAppKey() {
        Assert.hasText(this.appKey,
                "[Assertion failed appKey] - this String argument must have text; it must not be null, empty, or blank");
        return this.appKey;
    }

    /**
     * 获取默认的appSecret.
     * @return appSecret
     */
    public String getAppSecret() {
        return this.appSecret;
    }

    /**
     * 设置默认的appSecret.
     * @param appSecret appSecret
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     * 确定默认的appSecret.
     * @return appSecret
     */
    public String determineDefaultAppSecret() {
        Assert.hasText(this.appSecret,
                "[Assertion failed appSecret] - this String argument must have text; it must not be null, empty, or blank");
        return this.appSecret;
    }

    /**
     * 获取默认的expireIn.
     * @return 过期时间
     */
    public Long getExpireIn() {
        return this.expireIn;
    }

    /**
     * 设置默认的过期时间.
     * @param expireIn 过期时间
     */
    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }

    /**
     * 确定默认的expireIn.
     * @return 过期时间
     */
    public Long determineDefaultExpireIn() {
        if (null != this.expireIn) {
            return this.expireIn;
        }
        return DEFAULT_EXPIRE_IN;
    }

}
