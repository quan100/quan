package com.quan.tools.limit.autoconfigure;

import com.quan.tools.limit.executor.ILimitExecutor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 限流配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "limit")
public class LimitProperties {

    /**
     * 持有令牌的有效期
     * 当超过有效期时，令牌自动失效
     * 单位：毫秒
     */
    private Long leaseTime = 10000L;

    /**
     * 获取令牌的最大等待时间
     * 单位：毫秒
     */
    private Long waitTime = 1000L;

    /**
     * 流量控制执行器
     */
    private Class<? extends ILimitExecutor> executor;

    /**
     * 令牌的前缀
     */
    private String tokenPrefix = "limit";

    /**
     * 是否启用限流组件
     */
    private boolean enabled;

}
