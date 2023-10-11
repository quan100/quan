package com.quan.security.common.annotation;

import java.lang.annotation.*;

/**
 * 认证用户信息注解
 *
 * @author wangquan
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthUser {

    /**
     * 当开启检查时，若解析的认证用户信息失败，则抛出异常
     *
     * @return
     */
    boolean check() default true;
}
