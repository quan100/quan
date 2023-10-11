package com.quan.tools.crypto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * Configuration properties for captcha support.
 *
 * @author javaquan
 * @since 2.2.0
 */
@ConfigurationProperties(prefix = "quan.crypto")
public class CryptoProperties {

    private final static long DEFAULT_TIME_OUT = 3600L;
    private final static int DEFAULT_RETRY_COUNT = 5;
    private final static String DEFAULT_ALGORITHM_NAME = "md5";
    private final static int DEFAULT_ITERATIONS = 3;
    private final static String DEFAULT_REALM_NAME = "quan";

    /**
     * 默认缓存过期时间
     * 单位：秒
     */
    private Long timeOut;

    /**
     * 当密码输入错误时，允许重试的次数
     */
    private Integer retryCount;

    /**
     * 加密方式
     */
    private String algorithmName;


    /**
     * 迭代次数
     */
    private Integer hashIterations;

    /**
     * the realm from where the principal and credentials were acquired.
     */
    private String realmName;

    public Long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
    }

    public long determineDefaultTimeOut() {
        if (null != this.timeOut) {
            return this.timeOut;
        }
        return DEFAULT_TIME_OUT;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public int determineDefaultRetryCount() {
        if (null != this.retryCount) {
            return this.retryCount;
        }
        return DEFAULT_RETRY_COUNT;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String determineDefaultAlgorithmName() {
        if (StringUtils.hasText(this.algorithmName)) {
            return this.algorithmName;
        }
        return DEFAULT_ALGORITHM_NAME;
    }

    public Integer getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(Integer hashIterations) {
        this.hashIterations = hashIterations;
    }

    public int determineDefaultHashIterations() {
        if (null != this.hashIterations) {
            return this.hashIterations;
        }
        return DEFAULT_ITERATIONS;
    }

    public String getRealmName() {
        return realmName;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    public String determineDefaultRealmName() {
        if (StringUtils.hasText(this.realmName)) {
            return this.realmName;
        }
        return DEFAULT_REALM_NAME;
    }

}
