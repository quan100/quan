package cn.javaquan.cloud.gateway.auth.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

/**
 * HttpConfiguration.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Configuration
public class HttpConfiguration {

    /**
     * Bean used to manage the HttpMessageConverters used in a Spring Boot application.
     * Provides a convenient way to add and merge additional HttpMessageConverters to a
     * web application.
     * @param converters converters
     * @return httpMessageConverter
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

}
