package com.quan.tools.limit.exception;

/**
 * 流量控制自定义异常
 *
 * @author javaquan
 * @since 2.2.0
 */
public class LimitException extends RuntimeException {

    public LimitException() {
        super();
    }

    public LimitException(String message) {
        super(message);
    }

}
