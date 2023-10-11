package com.quan.security.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * token配置
 *
 * @author wangquan
 * @date 2021/5/31 20:27
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "quan.token")
public class TokenConfiguration {

    private String issuer;
    private String key;
    private String subject;
    private String claimsName;
}
