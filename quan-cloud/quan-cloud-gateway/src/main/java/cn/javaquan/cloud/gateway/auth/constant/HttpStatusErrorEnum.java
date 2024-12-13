package cn.javaquan.cloud.gateway.auth.constant;

import cn.javaquan.common.base.message.ResultType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 根据 HTTP 状态码定义错误信息枚举.
 *
 * @author javaquan
 * @since 2.3.1
 */
@Getter
public enum HttpStatusErrorEnum implements ResultType {

    /**
     * 参数错误.
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "参数错误"),
    /**
     * token 校验失败.
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "请先登录！"),
    /**
     * 无权限.
     */
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "无权限！"),
    /**
     * 请求不允许访问.
     */
    REQUEST_NOT_ALLOWED(HttpStatus.FORBIDDEN.value(), "无访问权限！"),
    /**
     * IP 不允许访问.
     */
    IP_NOT_ALLOWED(HttpStatus.FORBIDDEN.value(), "您所在的国家或地区无法访问！");

    /**
     * 错误码.
     */
    final Integer code;

    /**
     * 错误信息.
     */
    final String msg;

    /**
     * 构造方法.
     * @param code 错误码
     * @param msg 错误信息
     */
    HttpStatusErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

}
