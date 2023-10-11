package com.quan.tools.captcha.service;

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

    private final static int DEFAULT_MAX_LENGTH = 4;
    private final static int DEFAULT_MIN_LENGTH = 4;
    private final static String DEFAULT_CHARACTERS = "1234567890";
    private final static long DEFAULT_INVALID_TIME = 300;

    private Integer maxLength;
    private Integer minLength;
    private String characters;
    private Long invalidTime;

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public int determineDefaultMaxLength() {
        if (null != this.maxLength) {
            return this.maxLength;
        }
        return DEFAULT_MAX_LENGTH;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public int determineDefaultMinLength() {
        if (null != this.minLength) {
            return this.minLength;
        }
        return DEFAULT_MIN_LENGTH;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String determineDefaultCharacters() {
        if (StringUtils.hasText(this.characters)) {
            return this.characters;
        }
        return DEFAULT_CHARACTERS;
    }

    public Long getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Long invalidTime) {
        this.invalidTime = invalidTime;
    }

    public long determineDefaultInvalidTime() {
        if (null != this.invalidTime) {
            return this.invalidTime;
        }
        return DEFAULT_INVALID_TIME;
    }

}
