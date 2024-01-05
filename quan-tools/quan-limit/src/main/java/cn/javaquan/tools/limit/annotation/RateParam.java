package cn.javaquan.tools.limit.annotation;


import cn.javaquan.tools.limit.autoconfigure.LimitProperties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流速录配置注解
 *
 * @author javaquan
 * @since 2.2.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateParam {

    /**
     * 默认值：{@link LimitProperties#rate}
     *
     * @return 速率
     */
    long rate() default 0;

    /**
     * 默认值：{@link LimitProperties#rateInterval}
     *
     * @return 速率间隔，单位：秒
     */
    long rateInterval() default 0;

    /**
     * 若获取的值不为空，则排除当前规则
     *
     * @return 根据当前配置获取的值
     */
    String exclude() default "";

}
