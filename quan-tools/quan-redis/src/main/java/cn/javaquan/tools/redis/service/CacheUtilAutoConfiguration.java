package cn.javaquan.tools.redis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cn.javaquan.tools.redis.service.impl.RedisServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * CacheUtil工具配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@ConditionalOnClass({ CacheUtil.class })
public class CacheUtilAutoConfiguration {

    /**
     * 缓存工具配置.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({ StringRedisTemplate.class, ObjectMapper.class })
    protected static class CacheUtilConfiguration {

        /**
         * redis 服务实现.
         * @param template redis字符串操作模板
         * @param mapper json序列化器
         * @return redis 服务实现.
         */
        @Bean
        @Primary
        @ConditionalOnMissingBean
        public IRedisService redisService(StringRedisTemplate template, ObjectMapper mapper) {
            return new RedisServiceImpl(template, mapper);
        }

        @Bean
        @DependsOn({ "redisService" })
        CacheUtil id(IRedisService redisService) {
            return new CacheUtil(redisService);
        }

    }

}
