package cn.javaquan.tools.limiter.exception;

/**
 * 限制器自定义异常.
 *
 * @author javaquan
 * @since 2.2.0
 */
public class LimiterException extends RuntimeException {

    /**
     * 构造方法.
     */
    public LimiterException() {
        super();
    }

    /**
     * 构造方法.
     * @param message 错误信息
     */
    public LimiterException(String message) {
        super(message);
    }

    /**
     * 构造一个异常实例的方法.
     * <p>
     * 通过格式化模版生成错误信息
     * @param message 错误信息模版
     * @param params 参数
     * @return limiterException
     */
    public static LimiterException newInstance(String message, Object... params) {
        if (null != params) {
            message = String.format(message, params);
        }
        return new LimiterException(message);
    }

}
