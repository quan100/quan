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
import org.springframework.lang.Nullable;

/**
 * 基于 Redis 实现的限制器配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(LimiterProperties.class)
public class RedisLimiterAutoConfiguration {


    @Configuration(proxyBeanMethods = false)
    @Conditional(RedisLimiterCondition.class)
    protected static class RedisLimiterConfiguration {

        @Bean
        @ConditionalOnClass(IRedisService.class)
        public RedisLimiterExecutor redissonLimiterExecutor(@Nullable IRedisService redisService) {
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
