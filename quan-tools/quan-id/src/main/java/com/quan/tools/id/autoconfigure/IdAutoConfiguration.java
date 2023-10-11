package com.quan.tools.id.autoconfigure;

import com.quan.tools.id.ID;
import com.quan.tools.id.service.IdService;
import com.quan.tools.id.service.impl.IdServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * id生成参数配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(IdProperties.class)
public class IdAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    protected static class IdConfiguration {

        @Bean
        IdService idService(IdProperties properties) {
            return new IdServiceImpl(properties);
        }

        @Bean
        @DependsOn({"idService"})
        ID id(IdService idService) {
            return new ID(idService);
        }

    }

}
