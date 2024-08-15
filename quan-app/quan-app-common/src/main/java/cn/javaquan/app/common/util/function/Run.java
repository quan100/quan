package cn.javaquan.app.common.util.function;

/**
 * 表示一个运行过程的提供者.不要求返回结果.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FunctionalInterface
public interface Run {

    /**
     * 运行方法.
     */
    void apply();

}
