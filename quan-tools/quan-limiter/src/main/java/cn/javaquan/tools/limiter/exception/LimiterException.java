package cn.javaquan.tools.limiter.exception;

/**
 * 流量控制自定义异常
 *
 * @author javaquan
 * @since 2.2.0
 */
public class LimiterException extends RuntimeException {

    public LimiterException() {
        super();
    }

    public LimiterException(String message) {
        super(message);
    }

    public static LimiterException newInstance(String message, Object... params) {
        if (null != params) {
            message = String.format(message, params);
        }
        return new LimiterException(message);
    }

}
