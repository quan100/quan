package com.quan.tools.limit;

import com.quan.tools.limit.executor.ILimitExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author javaquan
 * @since 2.2.0
 */
@Data
@AllArgsConstructor
public class LimitInfo {

    /**
     * 自定义参数
     */
    private String params;

    /**
     * 持有令牌的有效期
     */
    private Long leaseTime;

    /**
     * 获取令牌超时时间
     */
    private Long waitTime;

    /**
     * 获取令牌返回的实例
     */
    private Object instance;

    /**
     * 流量控制执行器
     */
    private ILimitExecutor executor;
}
