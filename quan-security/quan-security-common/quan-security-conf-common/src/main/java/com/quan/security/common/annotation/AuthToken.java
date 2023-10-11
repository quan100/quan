package com.quan.security.common.annotation;

import java.lang.annotation.*;

/**
 * 认证Token注解
 *
 * @author wangquan
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthToken {
}
