package com.quan.tools.crypto;

import com.quan.tools.crypto.credential.RetryLimitHashedCredentialsMatcher;
import com.quan.tools.redis.service.IRedisService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 验证码工具配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(CryptoProperties.class)
public class CryptoAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({IRedisService.class})
    protected static class CryptoConfiguration {

        @Bean
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(IRedisService redisService, CryptoProperties properties) {
            return new RetryLimitHashedCredentialsMatcher(redisService, properties);
        }

        @Bean
        @DependsOn({"retryLimitHashedCredentialsMatcher"})
        PasswordService passwordService(RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher, CryptoProperties properties) {
            return new PasswordService(retryLimitHashedCredentialsMatcher, properties);
        }

    }

}
