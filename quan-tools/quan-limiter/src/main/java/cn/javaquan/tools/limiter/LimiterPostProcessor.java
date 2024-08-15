package cn.javaquan.tools.limiter;

import cn.javaquan.tools.limiter.exception.LimiterPostProcessorException;
import cn.javaquan.tools.limiter.executor.LimiterExecutor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 限制器执行后置处理器.
 *
 * @author javaquan
 * @since 2.2.0
 */
@Slf4j
@Data
public class LimiterPostProcessor<T> {

    /**
     * 令牌是否拥有执行权限.
     */
    private Boolean execute;

    /**
     * 令牌申请执行权限返回的实例.
     */
    private T instance;

    /**
     * 限制器的执行器.
     */
    private LimiterExecutor<T> executor;

    /**
     * 默认实例.
     */
    private LimiterPostProcessor() {
    }

    /**
     * 提供静态方法创建实例.
     * @param execute 令牌是否拥有执行权限
     * @param instance 令牌申请执行权限返回的实例
     * @param executor 限制器的执行器
     * @param <T> 约定的实例类型
     * @return limiterPostProcessor
     */
    public static <T> LimiterPostProcessor<T> newInstance(boolean execute, T instance, LimiterExecutor<T> executor) {
        notNull(instance, "Instance must not be null");
        notNull(executor, "Executor must not be null");

        return new LimiterPostProcessor<T>().executor(executor).execute(execute).instance(instance);
    }

    private LimiterPostProcessor<T> execute(Boolean execute) {
        this.execute = execute;
        return this;
    }

    private LimiterPostProcessor<T> instance(T instance) {
        this.instance = instance;
        return this;
    }

    private LimiterPostProcessor<T> executor(LimiterExecutor<T> executor) {
        this.executor = executor;
        return this;
    }

    /**
     * 释放限制器.
     * <p>
     * 如果令牌超过有效期将自动解除限制
     */
    public void release() {
        this.executor.release(this.getInstance());
    }

    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">notNull(clazz, "The class must not be null");</pre>
     * @param object the object to check
     * @param message the exception message to use if the assertion fails
     * @throws LimiterPostProcessorException if the object is {@code null}
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new LimiterPostProcessorException(message);
        }
    }

}
