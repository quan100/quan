package cn.javaquan.app.aggregate;

import cn.javaquan.app.aggregate.beans.QuanBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 应用聚合服务启动类.
 * <p>
 * 排除聚合应用中 {@link SpringBootApplication} 注解标识的类，避免重复扫描导致的自定义BeanNameGenerator 失效。
 *
 * @author javaquan
 * @since 1.0.0
 */
@ServletComponentScan(basePackages = "cn.javaquan.app.*")
@ComponentScan(basePackages = { "cn.javaquan" }, nameGenerator = QuanBeanNameGenerator.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { SpringBootApplication.class }))
@ConfigurationPropertiesScan(basePackages = { "cn.javaquan" })
@EnableEurekaClient
@EnableFeignClients(basePackages = { "cn.javaquan" })
@SpringBootApplication
public class QuanAppAggregationsApplication {

    /**
     * 使用默认设置从指定的源运行SpringApplication.
     * @param args 默认参数
     */
    public static void main(String[] args) {
        SpringApplication.run(QuanAppAggregationsApplication.class, args);
    }

}
