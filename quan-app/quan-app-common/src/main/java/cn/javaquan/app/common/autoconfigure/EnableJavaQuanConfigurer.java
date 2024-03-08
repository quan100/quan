package cn.javaquan.app.common.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ServletComponentScan(basePackages = "cn.javaquan.app.*")
@ComponentScan(basePackages = {"cn.javaquan"})
@ConfigurationPropertiesScan(basePackages = {"cn.javaquan"})
@EnableFeignClients(basePackages = {"cn.javaquan"})
public @interface EnableJavaQuanConfigurer {
}
