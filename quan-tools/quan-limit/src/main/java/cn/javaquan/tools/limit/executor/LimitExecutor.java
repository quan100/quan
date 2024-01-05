package cn.javaquan.tools.limit.executor;

import cn.javaquan.tools.limit.LimitInfo;
import cn.javaquan.tools.limit.autoconfigure.LimitProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 限流执行器初始化方法
 *
 * @author javaquan
 * @since 2.2.0
 */
@Slf4j
public class LimitExecutor implements InitializingBean {

    private final Map<Class<? extends ILimitExecutor>, ILimitExecutor> executorMap = new LinkedHashMap<>();
    @Setter
    private LimitProperties properties;
    @Setter
    private List<ILimitExecutor> executors;

    private ILimitExecutor limitExecutor;

    public LimitExecutor() {
    }

    /**
     * 流量控制
     *
     * @param token     生成的token
     * @param leaseTime 持有令牌的有效期
     * @param waitTime  获取令牌的最大等待时间
     * @return 限流信息
     */
    public LimitInfo limit(String token, long leaseTime, long waitTime) {
        return limit(token, leaseTime, waitTime, null);
    }

    /**
     * 流量控制
     *
     * @param token         生成的token
     * @param leaseTime     持有令牌的有效期
     * @param waitTime      获取令牌的最大等待时间
     * @param limitExecutor 限流执行器
     * @return 限流信息
     */
    public LimitInfo limit(String token, long leaseTime, long waitTime, Class<? extends ILimitExecutor> limitExecutor) {
        leaseTime = leaseTime == 0 ? properties.getLeaseTime() : leaseTime;
        waitTime = waitTime == 0 ? properties.getWaitTime() : waitTime;
        // 获取执行器
        ILimitExecutor executor = getExecutor(limitExecutor);
        Object instance = executor.limit(token, waitTime, leaseTime);
        if (null != instance) {
            return new LimitInfo(token, leaseTime, waitTime, instance, executor);
        }
        return null;
    }

    /**
     * 释放限流控制
     *
     * @param limitInfo
     * @return
     */
    public boolean releaseLimit(LimitInfo limitInfo) {
        if (null == limitInfo) {
            return false;
        }
        return limitInfo.getExecutor().releaseLimit(limitInfo.getInstance());
    }

    /**
     * 获取执行器
     *
     * @param clazz
     * @return
     */
    protected ILimitExecutor getExecutor(Class<? extends ILimitExecutor> clazz) {
        if (null == clazz || clazz == ILimitExecutor.class) {
            return limitExecutor;
        }
        final ILimitExecutor lockExecutor = executorMap.get(clazz);
        Assert.notNull(lockExecutor, String.format("无法获取执行器实例的类型：%s", clazz));
        return lockExecutor;
    }

    /**
     * 初始化限流配置
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.isTrue(properties.getWaitTime() > 0, "获取令牌的等待时间必须大于0");
        Assert.isTrue(properties.getLeaseTime() > 0, "令牌的租赁时间必须大于0");
        Assert.hasText(properties.getTokenPrefix(), "令牌的前缀不可为空");
        Assert.notEmpty(executors, "至少配置一个执行器");

        for (ILimitExecutor executor : executors) {
            executorMap.put(executor.getClass(), executor);
        }

        // 如果未配置限流执行器，则默认获取默认配置的首个执行器
        final Class<? extends ILimitExecutor> primaryExecutor = properties.getExecutor();
        if (null == primaryExecutor) {
            this.limitExecutor = executors.get(0);
        } else {
            this.limitExecutor = executorMap.get(primaryExecutor);
            Assert.notNull(this.limitExecutor, "限流执行器不可为空");
        }
    }
}
