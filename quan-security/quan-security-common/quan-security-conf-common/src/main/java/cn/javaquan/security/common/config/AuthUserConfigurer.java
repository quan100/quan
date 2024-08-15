package cn.javaquan.security.common.config;

import cn.javaquan.security.common.method.AuthTokenMethodArgumentResolver;
import cn.javaquan.security.common.method.AuthUserMethodArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 用户认证配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Configuration
public class AuthUserConfigurer implements WebMvcConfigurer {

    /**
     * 用户信息处理程序方法参数解析器.
     * @return 用户信息处理程序方法参数解析器
     */
    @Bean
    public AuthUserMethodArgumentResolver authUserMethodArgumentResolver() {
        return new AuthUserMethodArgumentResolver();
    }

    /**
     * 认证令牌处理程序方法参数解析器.
     * @return 认证TOKEN处理程序方法参数解析器
     */
    @Bean
    public AuthTokenMethodArgumentResolver authTokenMethodArgumentResolver() {
        return new AuthTokenMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authUserMethodArgumentResolver());
        resolvers.add(authTokenMethodArgumentResolver());
    }

}
