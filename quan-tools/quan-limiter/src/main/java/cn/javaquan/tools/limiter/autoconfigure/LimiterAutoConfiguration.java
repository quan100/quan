package cn.javaquan.tools.limiter.autoconfigure;

import cn.javaquan.tools.limiter.DefaultParamsResolver;
import cn.javaquan.tools.limiter.LimiterParamsResolver;
import cn.javaquan.tools.limiter.aop.LimiterAnnotationAdvisor;
import cn.javaquan.tools.limiter.aop.LimiterInterceptor;
import cn.javaquan.tools.limiter.executor.LimiterExecutor;
import cn.javaquan.tools.limiter.executor.LimiterExecutorInvocation;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * 限流自动配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(LimiterProperties.class)
public class LimiterAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @Conditional(LimiterCondition.class)
    protected static class LimiterConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public LimiterExecutorInvocation limiterExecutorInvocation(LimiterProperties properties, List<LimiterExecutor> executors) {
            LimiterExecutorInvocation invocation = new LimiterExecutorInvocation();
            invocation.setProperties(properties);
            invocation.setExecutors(executors);
            return invocation;
        }

        @Bean
        @ConditionalOnMissingBean
        public LimiterParamsResolver limiterParamsResolver() {
            return new DefaultParamsResolver();
        }

        @Bean
        @ConditionalOnMissingBean
        public LimiterInterceptor limiterInterceptor(LimiterProperties properties, LimiterParamsResolver resolver, LimiterExecutorInvocation invocation) {
            return new LimiterInterceptor(resolver, properties, invocation);
        }

        @Bean
        @ConditionalOnMissingBean
        public LimiterAnnotationAdvisor limiterAnnotationAdvisor(LimiterInterceptor interceptor) {
            return new LimiterAnnotationAdvisor(interceptor, Ordered.HIGHEST_PRECEDENCE);
        }

    }

    static class LimiterCondition extends AnyNestedCondition {

        LimiterCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "quan.tools.limiter", name = "enabled", havingValue = "true")
        static class EnabledProperty {

        }

    }

}
