package com.quan.security.common.config;

import com.quan.security.common.method.AuthTokenMethodArgumentResolver;
import com.quan.security.common.method.AuthUserMethodArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author wangquan
 */
@RequiredArgsConstructor
@Configuration
public class AuthUserConfigurer implements WebMvcConfigurer {

    @Bean
    public AuthUserMethodArgumentResolver authUserMethodArgumentResolver() {
        return new AuthUserMethodArgumentResolver();
    }

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
