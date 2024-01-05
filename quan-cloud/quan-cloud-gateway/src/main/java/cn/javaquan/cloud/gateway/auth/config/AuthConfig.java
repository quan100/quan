package cn.javaquan.cloud.gateway.auth.config;

import cn.javaquan.cloud.gateway.auth.constant.PermEnum;
import cn.javaquan.cloud.gateway.auth.factory.ChainDefinitionSource;
import cn.javaquan.cloud.gateway.auth.service.AuthSourceFeign;
import cn.javaquan.cloud.gateway.auth.service.impl.DefaultAuthSourceImpl;
import cn.javaquan.cloud.gateway.auth.filter.AuthFilterFactory;
import cn.javaquan.cloud.gateway.auth.service.IAuthSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.lang.Nullable;

/**
 * @author wangquan
 * @date 2020/3/10 14:24
 */
@Configuration
public class AuthConfig {

    /**
     * 获取默认资源配置
     *
     * @return
     */
    @ConditionalOnMissingBean
    @Bean("chainDefinitionSource")
    public ChainDefinitionSource chainDefinitionSource(@Nullable IAuthSource authSource) {
        ChainDefinitionSource definitionSource = new ChainDefinitionSource();
        definitionSource.setAuthSource(authSource);
        return definitionSource;
    }

    @ConditionalOnMissingBean
    @Bean
    public IAuthSource authSource(AuthSourceFeign authSourceFeign) {
        return new DefaultAuthSourceImpl(authSourceFeign);
    }

    /**
     * 获取Filter
     *
     * @return
     */
    @Bean
    @DependsOn({"chainDefinitionSource"})
    public AuthFilterFactory authFilterFactory(ChainDefinitionSource chainDefinitionSource, AuthSourceProperties authSourceProperties) {
        AuthFilterFactory filter = new AuthFilterFactory(chainDefinitionSource);
        filter.setFilterChainMap(authSourceProperties.isEnabled());
        filter.setFilters(PermEnum.filters);
        return filter;
    }
}
