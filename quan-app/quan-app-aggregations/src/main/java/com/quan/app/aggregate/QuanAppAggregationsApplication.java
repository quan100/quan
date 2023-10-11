package com.quan.app.aggregate;

import com.quan.app.aggregate.beans.QuanBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 应用聚合服务启动类
 * <p>
 * 排除聚合应用中 {@link SpringBootApplication} 注解标识的类，避免重复扫描导致的自定义BeanNameGenerator 失效。
 *
 * @author javaquan
 * @since 1.0.0
 */
@ServletComponentScan(basePackages = "com.quan.app.*")
@ComponentScan(
        basePackages = {"com.quan.app", "com.quan.security"}, nameGenerator = QuanBeanNameGenerator.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
                SpringBootApplication.class,
        })
)
@ConfigurationPropertiesScan(basePackages = {"com.quan.app", "com.quan.security"})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.quan.app", "com.quan.security"})
@SpringBootApplication
public class QuanAppAggregationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuanAppAggregationsApplication.class, args);
    }
}
