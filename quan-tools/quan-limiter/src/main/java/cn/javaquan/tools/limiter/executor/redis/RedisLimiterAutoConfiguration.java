package cn.javaquan.tools.limiter.executor.redis;

import cn.javaquan.tools.limiter.autoconfigure.LimiterProperties;
import cn.javaquan.tools.redis.service.IRedisService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 基于 Redis 实现的限制器配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(LimiterProperties.class)
public class RedisLimiterAutoConfiguration {

    /**
     * Redis 限制器配置.
     */
    @Configuration(proxyBeanMethods = false)
    @Conditional(RedisLimiterCondition.class)
    protected static class RedisLimiterConfiguration {

        /**
         * 创建 Redis 限制器执行器.
         * @param redisService redis 服务
         * @return redisLimiterExecutor
         */
        @Bean
        @ConditionalOnClass(IRedisService.class)
        public RedisLimiterExecutor redissonLimiterExecutor(IRedisService redisService) {
            return new RedisLimiterExecutor(redisService);
        }

    }

    static class RedisLimiterCondition extends AnyNestedCondition {

        RedisLimiterCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "quan.tools.limiter.redis", name = "enabled", havingValue = "true")
        static class EnabledProperty {

        }

    }

}
