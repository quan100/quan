package cn.javaquan.app.common.exception;

import cn.javaquan.app.common.constant.ErrorCodeEnum;
import lombok.Getter;

/**
 * 自定义系统异常.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Getter
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -8887652625723536544L;

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 错误信息.
     */
    private String msg;

    /**
     * 构造系统异常.
     * @param message 错误信息
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * 构造系统异常.
     * @param cause 异常
     */
    public SystemException(Exception cause) {
        super(cause);
    }

    /**
     * 构造系统异常.
     * @param message 错误信息
     * @param cause 异常
     */
    public SystemException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * 根据错误码和错误信息构造系统异常.
     * @param code 错误编码
     * @param msg 错误信息
     */
    public SystemException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据系统错误枚举构造系统异常.
     * @param errorCodeEnum 系统错误枚举
     */
    public SystemException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

}
