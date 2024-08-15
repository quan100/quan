package cn.javaquan.tools.limiter.exception;

/**
 * 限制器后置处理器异常.
 *
 * @author javaquan
 * @since 2.2.0
 */
public class LimiterPostProcessorException extends RuntimeException {

    /**
     * 构造方法.
     */
    public LimiterPostProcessorException() {
        super();
    }

    /**
     * 构造方法.
     * @param message 错误信息
     */
    public LimiterPostProcessorException(String message) {
        super(message);
    }

}
