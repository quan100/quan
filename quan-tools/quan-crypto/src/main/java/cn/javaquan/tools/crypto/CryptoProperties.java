package cn.javaquan.tools.crypto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * Configuration properties for crypto support.
 *
 * @author javaquan
 * @since 2.2.0
 */
@ConfigurationProperties(prefix = "quan.crypto")
public class CryptoProperties {

    private static final long DEFAULT_TIME_OUT = 3600L;

    private static final int DEFAULT_RETRY_COUNT = 5;

    private static final String DEFAULT_ALGORITHM_NAME = "md5";

    private static final int DEFAULT_ITERATIONS = 3;

    private static final String DEFAULT_REALM_NAME = "quan";

    /**
     * 默认缓存过期时间.
     * <p>
     * 单位：秒
     */
    private Long timeOut;

    /**
     * 当密码输入错误时，允许重试的次数.
     */
    private Integer retryCount;

    /**
     * 加密方式.
     */
    private String algorithmName;

    /**
     * 迭代次数.
     */
    private Integer hashIterations;

    /**
     * the realm from where the principal and credentials were acquired.
     */
    private String realmName;

    /**
     * 获取默认缓存过期时间.
     * @return 默认缓存过期时间.
     */
    public Long getTimeOut() {
        return this.timeOut;
    }

    /**
     * 设置默认缓存过期时间.
     * @param timeOut 默认缓存过期时间.
     */
    public void setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * 确定默认缓存过期时间.
     * @return 默认缓存过期时间.
     */
    public long determineDefaultTimeOut() {
        if (null != this.timeOut) {
            return this.timeOut;
        }
        return DEFAULT_TIME_OUT;
    }

    /**
     * 获取重试的次数.
     * @return 重试的次数.
     */
    public Integer getRetryCount() {
        return this.retryCount;
    }

    /**
     * 设置重试的次数.
     * @param retryCount 重试的次数.
     */
    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    /**
     * 确定默认的重试次数.
     * @return 默认的重试次数.
     */
    public int determineDefaultRetryCount() {
        if (null != this.retryCount) {
            return this.retryCount;
        }
        return DEFAULT_RETRY_COUNT;
    }

    /**
     * 获取加密方式.
     * @return 加密方式.
     */
    public String getAlgorithmName() {
        return this.algorithmName;
    }

    /**
     * 设置加密方式.
     * @param algorithmName 加密方式.
     */
    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    /**
     * 确定默认加密方式.
     * @return 默认加密方式.
     */
    public String determineDefaultAlgorithmName() {
        if (StringUtils.hasText(this.algorithmName)) {
            return this.algorithmName;
        }
        return DEFAULT_ALGORITHM_NAME;
    }

    /**
     * 获取迭代次数.
     * @return 迭代次数.
     */
    public Integer getHashIterations() {
        return this.hashIterations;
    }

    /**
     * 设置迭代次数.
     * @param hashIterations 迭代次数.
     */
    public void setHashIterations(Integer hashIterations) {
        this.hashIterations = hashIterations;
    }

    /**
     * 确定默认的迭代次数.
     * @return 默认的迭代次数.
     */
    public int determineDefaultHashIterations() {
        if (null != this.hashIterations) {
            return this.hashIterations;
        }
        return DEFAULT_ITERATIONS;
    }

    /**
     * 获取 realm name.
     * @return realm name.
     */
    public String getRealmName() {
        return this.realmName;
    }

    /**
     * 设置 realm name.
     * @param realmName realm name.
     */
    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    /**
     * 确定默认的 realm name.
     * @return 默认的 realm name.
     */
    public String determineDefaultRealmName() {
        if (StringUtils.hasText(this.realmName)) {
            return this.realmName;
        }
        return DEFAULT_REALM_NAME;
    }

}
