package cn.javaquan.tools.limit.autoconfigure;

import cn.javaquan.tools.limit.DefaultLimitParamBuilder;
import cn.javaquan.tools.limit.LimitParamBuilder;
import cn.javaquan.tools.limit.aop.LimitAnnotationAdvisor;
import cn.javaquan.tools.limit.aop.LimitInterceptor;
import cn.javaquan.tools.limit.executor.ILimitExecutor;
import cn.javaquan.tools.limit.executor.LimitExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * 限流自动配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@EnableConfigurationProperties(LimitProperties.class)
@ConditionalOnProperty(name = "limit.enabled", havingValue = "true")
@RequiredArgsConstructor
public class LimitAutoConfiguration {

    private final LimitProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public LimitExecutor limitExecutor(List<ILimitExecutor> executors) {
        LimitExecutor limitExecutor = new LimitExecutor();
        limitExecutor.setProperties(properties);
        limitExecutor.setExecutors(executors);
        return limitExecutor;
    }

    @Bean
    @ConditionalOnMissingBean
    public LimitParamBuilder limitParamBuilder() {
        return new DefaultLimitParamBuilder();
    }

    @Bean
    @ConditionalOnMissingBean
    public LimitInterceptor limitInterceptor(LimitParamBuilder limitParamBuilder, LimitExecutor limitExecutor) {
        return new LimitInterceptor(limitParamBuilder, properties, limitExecutor);
    }

    @Bean
    @ConditionalOnMissingBean
    public LimitAnnotationAdvisor limitAnnotationAdvisor(LimitInterceptor limitInterceptor) {
        return new LimitAnnotationAdvisor(limitInterceptor, Ordered.HIGHEST_PRECEDENCE);
    }

}
