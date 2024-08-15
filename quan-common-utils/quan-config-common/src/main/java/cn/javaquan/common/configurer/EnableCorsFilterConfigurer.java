package cn.javaquan.common.configurer;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 提供一个启用跨域过滤器配置的注释.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CorsFilterConfigurer.class)
public @interface EnableCorsFilterConfigurer {

}
