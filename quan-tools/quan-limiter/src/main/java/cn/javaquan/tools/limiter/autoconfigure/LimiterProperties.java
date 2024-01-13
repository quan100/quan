package cn.javaquan.tools.limiter.autoconfigure;

import cn.javaquan.tools.limiter.executor.LimiterExecutor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 限制器属性配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "quan.tools.limiter")
public class LimiterProperties {

    /**
     * 持有令牌的有效期
     * 当超过有效期时，令牌自动失效
     * 单位：毫秒
     */
    private Long leaseTime = 1000L;

    /**
     * 获取令牌的最大等待时间
     * 单位：毫秒
     */
    private Long waitTime = 1000L;

    /**
     * 自定义限制器执行器
     */
    private Class<? extends LimiterExecutor> executor;

    /**
     * 令牌的前缀
     */
    private String tokenPrefix = "quan:limiter";

    /**
     * 是否启用限流组件
     */
    private boolean enabled;

}
