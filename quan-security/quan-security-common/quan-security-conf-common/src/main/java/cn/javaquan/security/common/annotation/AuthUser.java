package cn.javaquan.security.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 认证用户信息注解.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthUser {

    /**
     * 当开启检查时，若解析的认证用户信息失败，则抛出异常.
     * @return 是否开启检查
     */
    boolean check() default true;

}
