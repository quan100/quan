package com.quan.tools.limit.executor;

/**
 * 限流执行器接口
 *
 * @author javaquan
 * @since 2.2.0
 */
public interface ILimitExecutor<T> {

    /**
     * 流量控制
     *
     * @param name      名称
     * @param waitTime  获取token的最大时间，超时将获取失败
     * @param leaseTime 持有令牌的有效期
     * @return 限流信息
     */
    T limit(String name, long waitTime, long leaseTime);

    /**
     * 释放流量控制
     *
     * @param instance 执行流量控制返回的实例
     * @return 是否释放成功
     */
    boolean releaseLimit(T instance);
}
