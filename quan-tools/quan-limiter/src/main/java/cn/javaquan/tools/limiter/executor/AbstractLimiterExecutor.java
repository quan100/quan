package cn.javaquan.tools.limiter.executor;


import cn.javaquan.tools.limiter.LimiterPostProcessor;

/**
 * 限制器执行抽象类
 *
 * @author javaquan
 * @since 2.2.0
 */
public abstract class AbstractLimiterExecutor<T> implements LimiterExecutor<T> {

    /**
     * 当限制器执行成功时，获取后置处理器
     */
    public LimiterPostProcessor<T> getPostProcessor(boolean execute, T instance) {
        return LimiterPostProcessor.newInstance(execute, instance, this);
    }
}
