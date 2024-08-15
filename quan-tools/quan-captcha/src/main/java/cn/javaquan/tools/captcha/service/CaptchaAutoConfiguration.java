package cn.javaquan.tools.captcha.service;

import cn.javaquan.tools.redis.service.IRedisService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码工具配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaAutoConfiguration {

    /**
     * 验证码工具配置.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({ IRedisService.class })
    protected static class CaptchaConfiguration {

        @Bean
        CaptchaService captchaService(IRedisService redisService, CaptchaProperties properties) {
            return new CaptchaService(redisService, properties);
        }

    }

}
