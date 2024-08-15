package cn.javaquan.tools.dingtalk.api;

import cn.javaquan.tools.dingtalk.api.ding.service.DingRobotService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 钉钉API配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(DingtalkProperties.class)
public class DingtalkAutoConfiguration {

    /**
     * 钉钉API配置.
     */
    @Configuration(proxyBeanMethods = false)
    protected static class DingtalkConfiguration {

        @Bean
        DingtalkApi dingtalkApi(DingtalkProperties properties) {
            return new DingtalkApi(properties);
        }

        @Bean
        DingRobotService dingRobotService() {
            return new DingRobotService();
        }

    }

}
