package com.quan.app.common.exception;

import com.quan.app.common.constant.ErrorCodeEnum;

/**
 * 自定义异常
 *
 * @author javaquan
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -8887652625723536544L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public SystemException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
