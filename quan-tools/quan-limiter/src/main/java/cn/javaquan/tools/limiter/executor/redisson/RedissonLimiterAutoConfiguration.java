package cn.javaquan.tools.limiter.executor.redisson;

import cn.javaquan.tools.limiter.autoconfigure.LimiterProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 基于 Redisson 实现的限制器配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(LimiterProperties.class)
public class RedissonLimiterAutoConfiguration {

    /**
     * Redisson 限制器配置.
     */
    @Configuration(proxyBeanMethods = false)
    @Conditional(RedissonLimiterCondition.class)
    protected static class RedissonLimiterConfiguration {

        /**
         * Redisson 限制器执行器.
         * @param redissonClient redisson 客户端
         * @return redissonLimiterExecutor
         */
        @Bean
        @Order(100)
        @ConditionalOnClass(Redisson.class)
        public RedissonLimiterExecutor redissonLimiterExecutor(RedissonClient redissonClient) {
            return new RedissonLimiterExecutor(redissonClient);
        }

    }

    static class RedissonLimiterCondition extends AnyNestedCondition {

        RedissonLimiterCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "quan.tools.limiter.redisson", name = "enabled", havingValue = "true")
        static class EnabledProperty {

        }

    }

}
