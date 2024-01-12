package cn.javaquan.tools.limiter.exception;

/**
 * 限制器后置处理器异常
 *
 * @author javaquan
 * @since 2.2.0
 */
public class LimiterPostProcessorException extends RuntimeException {

    public LimiterPostProcessorException() {
        super();
    }

    public LimiterPostProcessorException(String message) {
        super(message);
    }

}
