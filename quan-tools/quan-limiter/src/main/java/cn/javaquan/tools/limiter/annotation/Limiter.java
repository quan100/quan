package cn.javaquan.tools.limiter.annotation;


import cn.javaquan.tools.limiter.DefaultParamsResolver;
import cn.javaquan.tools.limiter.autoconfigure.LimiterProperties;
import cn.javaquan.tools.limiter.exception.LimiterException;
import cn.javaquan.tools.limiter.executor.LimiterExecutor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限制器注解
 *
 * @author javaquan
 * @since 2.2.0
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Limiter {

    /**
     * 令牌的名称
     * 默认由方法的全限定名 + 方法名组成
     * <p>
     * 令牌名称是令牌的组成部分，令牌的组成由
     * {@link LimiterProperties#getTokenPrefix()} + {@code name} + {@link #params} + {@link #arrayParams}
     * 构成。
     *
     * @return 令牌前缀
     */
    String name() default "";

    /**
     * @return 执行器的具体实现类
     */
    Class<? extends LimiterExecutor> executor() default LimiterExecutor.class;

    /**
     * 令牌参数的表达式
     * <p>
     * 通过令牌参数模版，从方法的请求参数中解析具体的参数值，并拼接到令牌后面。
     * 当配置多个参数时，按照顺序拼接。
     * <p>
     * 默认的参数解析器 {@link DefaultParamsResolver}，支持 SpEL 表达式。
     *
     * @return 令牌参数的SpEL表达式
     */
    String[] params() default {};

    /**
     * 令牌参数 {@link #params} 是否可以为空
     * <p>
     * true: 令牌参数可以为空，即任何情况下，均会创建令牌。
     * false: 令牌参数不可以为空，要求令牌参数必须存在。如果令牌参数不存在，则不会创建令牌；意味着限制器将不生效。
     *
     * @return 令牌参数是否可以为空
     */
    boolean nullable() default true;

    /**
     * 令牌的有效期，单位：毫秒
     * 默认值：{@link LimiterProperties#getLeaseTime()}
     *
     * @return 令牌的有效期
     */
    long leaseTime() default 0L;

    /**
     * 获取令牌的最大等待时间，单位：毫秒
     * 默认值：{@link LimiterProperties#getWaitTime()}
     *
     * @return 获取令牌的最大等待时间
     */
    long waitTime() default 0L;

    /**
     * 令牌数组参数的表达式
     * <p>
     * 该模版会按照数组类型解析模版的值。要求配置的模版目标参数为数组类型。
     * 获取到数组类型的参数值后，会根据数组中的每一条记录拼接到令牌后面生成一个新的令牌。之后会得到与数组数量相同的令牌。
     * <p>
     * 默认的参数解析器 {@link DefaultParamsResolver}，支持 SpEL 表达式。
     * <p>
     * 需注意当元素数量过 {@link #arrayParamsThreshold} 时，超出的参数不会生成 token 。
     * 若配置的表达式模版解析后的参数值不是数组类型时将会被转换为数组。
     *
     * @return 令牌数组参数的SpEL表达式
     */
    String[] arrayParams() default {};

    /**
     * 令牌数组参数的阀值
     * <p>
     * 只有当配置 {@link #arrayParams} 参数时，该配置生效。默认值为 {@code -1}，表示不限制。
     * 当根据 {@link #arrayParams} 模版解析的数组元素数量超过阀值时，超出的参数不会生成 token 。
     *
     * @return 令牌数组参数的阀值
     */
    int arrayParamsThreshold() default -1;

    /**
     * 当请求被限制器拦截，默认抛出 {@link LimiterException} 异常。通过该参数可以定义异常信息。
     * 支持 {@link String#format(String, Object...)} 格式化信息内容。可选的格式化参数为 {@link #params} 模版解析的参数值。
     *
     * @return 默认的异常信息
     */
    String message() default "请求过于频繁";

    /**
     * 程序执行完成后，是否自动释放令牌
     * <p>
     * 自动释放的场景：
     * 1. 当程序执行完成后，自动释放。
     * 2. 当令牌有效期超过 {@link #leaseTime} 后，自动释放。
     * <p>
     * 当配置值为 {@code false} 时，必须等待令牌有效期超过 {@link #leaseTime} 后释放。
     *
     * @return 是否自动释放锁
     */
    boolean automaticReleaseLock() default true;

}
