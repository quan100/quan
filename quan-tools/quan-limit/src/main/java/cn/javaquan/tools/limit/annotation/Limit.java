package cn.javaquan.tools.limit.annotation;


import cn.javaquan.tools.limit.executor.ILimitExecutor;
import cn.javaquan.tools.limit.executor.redisson.RedissonLimitExecutor;
import cn.javaquan.tools.limit.autoconfigure.LimitProperties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口限流注解
 *
 * @author javaquan
 * @since 2.2.0
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Limit {

    /**
     * 生成令牌的名称
     * 为空时默认取全路径
     *
     * @return 名称
     */
    String name() default "";

    /**
     * 默认使用{@link RedissonLimitExecutor}执行器
     *
     * @return 流量控制执行器
     */
    Class<? extends ILimitExecutor> executor() default ILimitExecutor.class;

    /**
     * 自定义令牌参数，拼接到令牌后面
     * 支持 SEPL，多个按顺序拼接
     * 令牌 = name + params
     *
     * @return param
     */
    String[] params() default {};

    /**
     * true: 要求令牌参数必须存在。如果令牌参数获取为空，则跳过令牌创建，即不会限流。
     * false: 不要求令牌参数存在，即任何情况下，均会创建令牌。
     *
     * @return 是否要求令牌参数必须存在
     */
    boolean existParam() default false;

    /**
     * 持有令牌的有效期
     * 默认值：{@link LimitProperties#leaseTime}
     *
     * @return 单位：毫秒
     */
    long leaseTime() default 0L;

    /**
     * 默认值：{@link LimitProperties#waitTime}
     *
     * @return 获取令牌的最大等待时间 单位：毫秒
     */
    long waitTime() default 0L;

    /**
     * 支持 SEPL
     * <p>
     * 当{@link #params} 获取的参数，指定拆分属性的类型为数组时，拆分每一个元素。
     * 每一个属性均生成一个令牌。令牌前缀默认为未拆分的属性组合。
     * 需注意，当元素数量过阀值时，参数失效.
     * 属性非集合时，参数失效.
     *
     * @return
     */
    String[] splitParams() default {};

    /**
     * 拆分数组的阀值，当数组长度大于该阀值时，忽略拆分条件.
     * {@link #splitParams()} 值不为空时生效.
     *
     * @return
     */
    int splitThreshold() default 10;

    /**
     * 当请求无法获取执行令牌时，抛出的异常信息
     *
     * @return
     */
    String message() default "请求过于频繁";

    /**
     * 程序执行完成后，是否自动释放锁
     *
     * @return
     */
    boolean automaticReleaseLock() default true;

}
