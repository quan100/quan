package cn.javaquan.app.common.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用 {@code cn.javaquan} 配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ServletComponentScan(basePackages = "cn.javaquan.app.*")
@ComponentScan(basePackages = { "cn.javaquan" })
@ConfigurationPropertiesScan(basePackages = { "cn.javaquan" })
@EnableFeignClients(basePackages = { "cn.javaquan" })
public @interface EnableJavaQuanConfigurer {

}
