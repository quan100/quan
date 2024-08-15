package cn.javaquan.tools.limiter.executor.redis;

import cn.javaquan.tools.limiter.LimiterPostProcessor;
import cn.javaquan.tools.limiter.executor.AbstractLimiterExecutor;
import cn.javaquan.tools.redis.service.IRedisService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * 基于 Redis 实现的限制器执行器.
 *
 * @author javaquan
 * @since 2.2.0
 */
@RequiredArgsConstructor
public class RedisLimiterExecutor extends AbstractLimiterExecutor<String> {

    private final IRedisService redisService;

    private static final long INTERVAL_TIME = 100;

    @Override
    public LimiterPostProcessor<String> execute(String token, long waitTime, long leaseTime) throws Exception {
        final boolean locked = getLock(token, waitTime, leaseTime);
        return getPostProcessor(locked, token);
    }

    @Override
    public boolean release(String instance) {
        final boolean locked = redisService.exists(instance);
        if (locked) {
            redisService.del(instance);
            return true;
        }
        return false;
    }

    private boolean getLock(String token, long waitTime, long leaseTime) throws InterruptedException {
        final boolean locked = redisService.exists(token);
        if (!locked) {
            return redisService.setNx(token, true, leaseTime, TimeUnit.MILLISECONDS);
        }

        Thread.sleep(INTERVAL_TIME);

        if (waitTime <= 0) {
            return false;
        }

        return getLock(token, waitTime - INTERVAL_TIME, leaseTime);
    }

}
