package com.quan.common.configurer;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CorsFilterConfigurer.class)
public @interface EnableCorsFilterConfigurer {
}