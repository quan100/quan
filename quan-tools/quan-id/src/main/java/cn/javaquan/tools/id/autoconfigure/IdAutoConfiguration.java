package cn.javaquan.tools.id.autoconfigure;

import cn.javaquan.tools.id.ID;
import cn.javaquan.tools.id.service.IdService;
import cn.javaquan.tools.id.service.impl.IdServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * id生成参数配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(IdProperties.class)
public class IdAutoConfiguration {

    /**
     * id生成器配置.
     */
    @Configuration(proxyBeanMethods = false)
    protected static class IdConfiguration {

        @Bean("quanToolsIdService")
        IdService idService(IdProperties properties) {
            return new IdServiceImpl(properties);
        }

        @Bean("quanToolsId")
        ID id(IdService idService) {
            return new ID(idService);
        }

    }

}
