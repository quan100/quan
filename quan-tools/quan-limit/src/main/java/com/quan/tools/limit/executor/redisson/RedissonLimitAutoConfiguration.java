package com.quan.tools.limit.executor.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;

/**
 * 基于Redisson限流自动化配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@ConditionalOnProperty(name = "limit.enabled", havingValue = "true")
@Configuration
@ConditionalOnClass(Redisson.class)
class RedissonLimitAutoConfiguration {

    @Bean
    @Order(100)
    public RedissonLimitExecutor redissonLimitExecutor(@Nullable RedissonClient redissonClient) {
        return new RedissonLimitExecutor(redissonClient);
    }
}
