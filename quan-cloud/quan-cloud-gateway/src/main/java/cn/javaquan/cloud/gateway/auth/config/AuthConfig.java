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

/**
 * 认证服务配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Configuration
public class AuthConfig {

    /**
     * 默认资源配置.
     * @param authSource 权限处理接口
     * @return 权限资源处理器
     */
    @ConditionalOnMissingBean
    @Bean("chainDefinitionSource")
    public ChainDefinitionSource chainDefinitionSource(IAuthSource authSource) {
        ChainDefinitionSource definitionSource = new ChainDefinitionSource();
        definitionSource.setAuthSource(authSource);
        return definitionSource;
    }

    /**
     * 权限资源处理器.
     * @param authSourceFeign 权限处理接口
     * @return 权限资源处理器
     */
    @ConditionalOnMissingBean
    @Bean
    public IAuthSource authSource(AuthSourceFeign authSourceFeign) {
        return new DefaultAuthSourceImpl(authSourceFeign);
    }

    /**
     * 权限过滤器.
     * @param chainDefinitionSource 权限资源处理器
     * @param authSourceProperties 权限配置
     * @return 权限过滤器
     */
    @Bean
    @DependsOn({ "chainDefinitionSource" })
    public AuthFilterFactory authFilterFactory(ChainDefinitionSource chainDefinitionSource,
            AuthSourceProperties authSourceProperties) {
        AuthFilterFactory filter = new AuthFilterFactory(chainDefinitionSource);
        filter.setFilterChainMap(authSourceProperties.isEnabled());
        filter.setFilters(PermEnum.filters);
        return filter;
    }

}
