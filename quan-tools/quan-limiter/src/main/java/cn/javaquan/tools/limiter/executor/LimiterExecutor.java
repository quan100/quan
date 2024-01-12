package cn.javaquan.tools.limiter.executor;

import cn.javaquan.tools.limiter.LimiterPostProcessor;

/**
 * 限制器执行接口
 *
 * @param <T> 约定的限制器执行返回的参数类型
 * @author javaquan
 * @since 2.2.0
 */
public interface LimiterExecutor<T> {

    /**
     * 根据令牌执行限制器，申请执行权限
     *
     * @param token     令牌
     * @param waitTime  获取令牌的最大时间，超时将获取失败，单位：毫秒
     * @param leaseTime 持有令牌的时间，单位：毫秒
     * @return 限制器执行返回的后置处理器
     */
    LimiterPostProcessor<T> execute(String token, long waitTime, long leaseTime) throws Exception;

    /**
     * 释放限制器
     *
     * @param instance {@link LimiterPostProcessor#getInstance()}
     * @return 是否释放成功
     */
    boolean release(T instance);

}
