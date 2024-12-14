package cn.javaquan.tools.sensitive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 敏感字段注解. 用于敏感字段数据脱敏.
 * <p>
 * 只能作用在 String 类型上，其它类型将不生效.
 *
 * @author javaquan
 * @since 2.3.2
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldSensitive {

    /**
     * 脱敏规则的正则表达式.
     * <p>
     * 若配置多个规则，默认按顺序进行匹配。首个匹配上的规则生效.
     * @return 正则表达式
     */
    SensitiveRuleEnum[] rule() default { SensitiveRuleEnum.TEXT };

}
