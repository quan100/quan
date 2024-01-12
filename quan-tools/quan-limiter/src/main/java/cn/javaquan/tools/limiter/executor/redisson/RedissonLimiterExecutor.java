package cn.javaquan.tools.limiter.executor.redisson;

import cn.javaquan.tools.limiter.LimiterPostProcessor;
import cn.javaquan.tools.limiter.executor.AbstractLimiterExecutor;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Redisson 实现的限制器执行器
 *
 * @author javaquan
 * @since 2.2.0
 */
@RequiredArgsConstructor
public class RedissonLimiterExecutor extends AbstractLimiterExecutor<RLock> {

    private final RedissonClient redissonClient;

    @Override
    public LimiterPostProcessor<RLock> execute(String token, long waitTime, long leaseTime) throws Exception {
        final RLock lockInstance = redissonClient.getLock(token);
        final boolean locked = lockInstance.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
        return getPostProcessor(locked, lockInstance);
    }

    @Override
    public boolean release(RLock instance) {
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
