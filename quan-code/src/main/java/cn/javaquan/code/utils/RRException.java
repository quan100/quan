package cn.javaquan.code.utils;

import lombok.Data;

/**
 * 自定义异常
 *
 * @author chenshun/sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
public class RRException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误信息.
     */
    private String msg;

    /**
     * 错误码.
     */
    private int code = 500;

    /**
     * RRException.
     * @param msg 错误信息
     * @param e 异常
     */
    public RRException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

}
