package cn.javaquan.tools.captcha.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * Configuration properties for captcha support.
 *
 * @author javaquan
 * @since 2.2.0
 */
@ConfigurationProperties(prefix = "quan.captcha")
public class CaptchaProperties {

    /**
     * 默认验证码最大长度.
     */
    private static final int DEFAULT_MAX_LENGTH = 4;

    /**
     * 默认验证码最小长度.
     */
    private static final int DEFAULT_MIN_LENGTH = 4;

    /**
     * 默认组成验证码的字符.
     */
    private static final String DEFAULT_CHARACTERS = "1234567890";

    /**
     * 默认验证码失效时间.
     * <p>
     * 单位：秒
     */
    private static final long DEFAULT_INVALID_TIME = 300;

    /**
     * 验证码最大长度.
     */
    private Integer maxLength;

    /**
     * 验证码最小长度.
     */
    private Integer minLength;

    /**
     * 验证码组成字符.
     */
    private String characters;

    /**
     * 验证码失效时间.
     * <p>
     * 单位：秒
     */
    private Long invalidTime;

    /**
     * 获取验证码最大长度.
     * @return 验证码最大长度
     */
    public Integer getMaxLength() {
        return this.maxLength;
    }

    /**
     * 设置验证码最大长度.
     * @param maxLength 验证码最大长度
     */
    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * 确定验证码最大长度.
     * @return 验证码最大长度
     */
    public int determineDefaultMaxLength() {
        if (null != this.maxLength) {
            return this.maxLength;
        }
        return DEFAULT_MAX_LENGTH;
    }

    /**
     * 获取验证码最小长度.
     * @return 验证码最小长度
     */
    public Integer getMinLength() {
        return this.minLength;
    }

    /**
     * 设置验证码最小长度.
     * @param minLength 验证码最小长度
     */
    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    /**
     * 确定验证码最小长度.
     * @return 验证码最小长度
     */
    public int determineDefaultMinLength() {
        if (null != this.minLength) {
            return this.minLength;
        }
        return DEFAULT_MIN_LENGTH;
    }

    /**
     * 获取组成验证码的字符.
     * @return 组成验证码的字符
     */
    public String getCharacters() {
        return this.characters;
    }

    /**
     * 设置组成验证码的字符.
     * @param characters 组成验证码的字符
     */
    public void setCharacters(String characters) {
        this.characters = characters;
    }

    /**
     * 确定字符配置。 当字符配置为空时，获取默认配置.
     * @return 字符配置
     */
    public String determineDefaultCharacters() {
        if (StringUtils.hasText(this.characters)) {
            return this.characters;
        }
        return DEFAULT_CHARACTERS;
    }

    /**
     * 获取失效时间配置.
     * @return 失效时间配置
     */
    public Long getInvalidTime() {
        return this.invalidTime;
    }

    /**
     * 设置失效时间配置.
     * @param invalidTime 失效时间配置
     */
    public void setInvalidTime(Long invalidTime) {
        this.invalidTime = invalidTime;
    }

    /**
     * 确定失效时间配置.
     * @return 失效时间配置
     */
    public long determineDefaultInvalidTime() {
        if (null != this.invalidTime) {
            return this.invalidTime;
        }
        return DEFAULT_INVALID_TIME;
    }

}
