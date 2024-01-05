package cn.javaquan.tools.limit.executor.redisson;

import cn.javaquan.tools.limit.executor.AbstractLimitExecutor;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 限流执行器
 * 基于Redisson实现
 *
 * @author javaquan
 * @since 2.2.0
 */
@RequiredArgsConstructor
public class RedissonLimitExecutor extends AbstractLimitExecutor<RLock> {

    private final RedissonClient redissonClient;

    /**
     * 执行限流
     *
     * @param name      名称
     * @param waitTime  获取令牌的最大等待时间，超时将获取失败
     * @param leaseTime 持有令牌的有效期
     * @return
     */
    @Override
    public RLock limit(String name, long waitTime, long leaseTime) {
        try {
            final RLock lockInstance = redissonClient.getLock(name);
            final boolean locked = lockInstance.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
            return limitInstance(locked, lockInstance);
        } catch (InterruptedException e) {
            return null;
        }
    }

    /**
     * 释放限流
     *
     * @param instance 执行限流返回的实例
     * @return
     */
    @Override
    public boolean releaseLimit(RLock instance) {
        if (instance.isHeldByCurrentThread()) {
            try {
                return instance.forceUnlockAsync().get();
            } catch (ExecutionException | InterruptedException e) {
                return false;
            }
        }
        return false;
    }
}
