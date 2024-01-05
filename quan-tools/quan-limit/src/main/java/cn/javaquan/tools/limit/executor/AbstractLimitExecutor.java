package cn.javaquan.tools.limit.executor;

/**
 * 限流执行器抽象实现
 *
 * @author javaquan
 * @since 2.2.0
 */
public abstract class AbstractLimitExecutor<T> implements ILimitExecutor<T> {

    protected T limitInstance(boolean limit, T instance) {
        return limit ? instance : null;
    }
}
