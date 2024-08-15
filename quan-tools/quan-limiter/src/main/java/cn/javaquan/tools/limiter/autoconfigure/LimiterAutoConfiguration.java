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
 * 限制器自动配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(LimiterProperties.class)
public class LimiterAutoConfiguration {

    /**
     * 限制器配置.
     */
    @Configuration(proxyBeanMethods = false)
    @Conditional(LimiterCondition.class)
    protected static class LimiterConfiguration {

        /**
         * 限制器的执行器实例.
         * @param properties 限制器的配置参数
         * @param executors 执行限制请求的执行器列表
         * @return limiterExecutorInvocation
         */
        @Bean
        @ConditionalOnMissingBean
        public LimiterExecutorInvocation limiterExecutorInvocation(LimiterProperties properties,
                List<LimiterExecutor<?>> executors) {
            LimiterExecutorInvocation invocation = new LimiterExecutorInvocation();
            invocation.setProperties(properties);
            invocation.setExecutors(executors);
            return invocation;
        }

        /**
         * 接口参数解析实例.
         * @return limiterParamsResolver
         */
        @Bean
        @ConditionalOnMissingBean
        public LimiterParamsResolver limiterParamsResolver() {
            return new DefaultParamsResolver();
        }

        /**
         * 限制器拦截器实例.
         * @param properties 限制器的配置参数
         * @param resolver 参数解析器
         * @param invocation 执行限制请求的执行器
         * @return limiterInterceptor
         */
        @Bean
        @ConditionalOnMissingBean
        public LimiterInterceptor limiterInterceptor(LimiterProperties properties, LimiterParamsResolver resolver,
                LimiterExecutorInvocation invocation) {
            return new LimiterInterceptor(resolver, properties, invocation);
        }

        /**
         * 限制器注解拦截器.
         * @param interceptor 限制器拦截器
         * @return limiterAnnotationAdvisor
         */
        @Bean
        @ConditionalOnMissingBean
        public LimiterAnnotationAdvisor limiterAnnotationAdvisor(LimiterInterceptor interceptor) {
            return new LimiterAnnotationAdvisor(interceptor, Ordered.HIGHEST_PRECEDENCE);
        }

    }

    /**
     * 条件配置.
     */
    static class LimiterCondition extends AnyNestedCondition {

        LimiterCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "quan.tools.limiter", name = "enabled", havingValue = "true")
        static class EnabledProperty {

        }

    }

}
