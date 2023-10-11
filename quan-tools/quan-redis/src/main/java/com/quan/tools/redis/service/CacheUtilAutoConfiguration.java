package com.quan.tools.redis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.tools.redis.service.impl.RedisServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * CacheUtil工具配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@ConditionalOnClass({CacheUtil.class})
public class CacheUtilAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({StringRedisTemplate.class, ObjectMapper.class})
    protected static class CacheUtilConfiguration {

        @Bean
        @Primary
        @ConditionalOnMissingBean
        public IRedisService redisService(StringRedisTemplate template, ObjectMapper mapper) {
            return new RedisServiceImpl(template, mapper);
        }

        @Bean
        @DependsOn({"redisService"})
        CacheUtil id(IRedisService redisService) {
            return new CacheUtil(redisService);
        }

    }

}
