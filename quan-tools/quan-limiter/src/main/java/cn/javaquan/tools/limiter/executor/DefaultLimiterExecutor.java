package cn.javaquan.tools.limiter.executor;

import cn.javaquan.tools.limiter.LimiterPostProcessor;

/**
 * 默认的限流执行器.
 *
 * @author javaquan
 * @since 1.0.0
 */
public class DefaultLimiterExecutor extends AbstractLimiterExecutor<Object> {

    @Override
    public LimiterPostProcessor<Object> execute(String token, long waitTime, long leaseTime) throws Exception {
        return null;
    }

    @Override
    public boolean release(Object instance) {
        return false;
    }

}
