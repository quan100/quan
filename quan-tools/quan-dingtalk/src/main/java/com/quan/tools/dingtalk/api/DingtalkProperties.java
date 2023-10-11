package com.quan.tools.dingtalk.api;

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

    private final static Long DEFAULT_EXPIRE_IN = 2592000L;

    private String appKey;
    private String appSecret;
    private Long expireIn;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String determineDefaultAppKey() {
        Assert.hasText(this.appKey, "[Assertion failed appKey] - this String argument must have text; it must not be null, empty, or blank");
        return this.appKey;
    }


    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String determineDefaultAppSecret() {
        Assert.hasText(this.appKey, "[Assertion failed appSecret] - this String argument must have text; it must not be null, empty, or blank");
        return this.appKey;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }

    public Long determineDefaultExpireIn() {
        if (null != this.expireIn) {
            return this.expireIn;
        }
        return DEFAULT_EXPIRE_IN;
    }


}
