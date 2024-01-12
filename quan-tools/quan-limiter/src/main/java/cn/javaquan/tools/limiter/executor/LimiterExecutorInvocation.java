package cn.javaquan.tools.limiter.executor;

import cn.javaquan.tools.limiter.LimiterPostProcessor;
import cn.javaquan.tools.limiter.autoconfigure.LimiterProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 限制器的执行器适配方法
 * <p>
 * 通过实现 {@link LimiterExecutor} 接口，可适配执行器
 *
 * @author javaquan
 * @since 2.2.0
 */
@Slf4j
public class LimiterExecutorInvocation implements InitializingBean {

    private final Map<Class<? extends LimiterExecutor>, LimiterExecutor> executorMap = new LinkedHashMap<>();
    @Setter
    private LimiterProperties properties;
    @Setter
    private List<LimiterExecutor> executors;

    private LimiterExecutor executor;

    public LimiterExecutorInvocation() {
    }

    /**
     * 调用限制器执行方法
     * <p>
     * 限制器执行完成后返回一个后置处理器，在方法执行完成后，调用后置处理器执行。
     * <p>
     * 当令牌重复申请执行权限时返回空值。
     *
     * @param token           令牌
     * @param leaseTime       令牌的有效期
     * @param waitTime        获取令牌的最大等待时间
     * @param limiterExecutor 限制器的执行器
     * @return 后置处理器
     */
    public LimiterPostProcessor invoke(String token, long leaseTime, long waitTime, Class<? extends LimiterExecutor> limiterExecutor) throws Exception {
        leaseTime = leaseTime == 0 ? properties.getLeaseTime() : leaseTime;
        waitTime = waitTime == 0 ? properties.getWaitTime() : waitTime;
        return getExecutor(limiterExecutor).execute(token, waitTime, leaseTime);
    }

    /**
     * 获取执行器
     *
     * @param clazz
     * @return
     */
    protected LimiterExecutor getExecutor(Class<? extends LimiterExecutor> clazz) {
        if (null == clazz || clazz == LimiterExecutor.class) {
            return executor;
        }
        final LimiterExecutor lockExecutor = executorMap.get(clazz);
        Assert.notNull(lockExecutor, String.format("无法获取执行器实例的类型：%s", clazz));
        return lockExecutor;
    }

    /**
     * 初始化限制器配置
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.isTrue(properties.getWaitTime() > 0, "获取令牌的等待时间必须大于0");
        Assert.isTrue(properties.getLeaseTime() > 0, "令牌的租赁时间必须大于0");
        Assert.hasText(properties.getTokenPrefix(), "令牌的前缀不可为空");
        Assert.notEmpty(executors, "至少配置一个执行器");

        for (LimiterExecutor executor : executors) {
            executorMap.put(executor.getClass(), executor);
        }

        // 如果未配置执行器，则默认获取默认执行器
        final Class<? extends LimiterExecutor> primaryExecutor = properties.getExecutor();
        if (null == primaryExecutor) {
            this.executor = executors.get(0);
        } else {
            this.executor = executorMap.get(primaryExecutor);
            Assert.notNull(this.executor, "限流执行器不可为空");
        }
    }
}
