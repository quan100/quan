package cn.javaquan.tools.dingtalk.api;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码工具配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(DingtalkProperties.class)
public class DingtalkAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    protected static class DingtalkConfiguration {

        @Bean
        DingtalkApi dingtalkApi(DingtalkProperties properties) {
            return new DingtalkApi(properties);
        }

    }

}
