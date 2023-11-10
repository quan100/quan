package com.quan.common.base.message;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;


/**
 * 统一响应参数
 *
 * @author wangquan
 * @date 2020/3/10 00:16
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 485088578586623310L;
    public static final String MESSAGE_TYPE = "serialMESSAGE_";

    private Integer code;

    private String type;

    private String message;

    private T data;

    private Long serialMESSAGE_ = System.currentTimeMillis();

    public Result(Integer code, String type, String message, T data) {
        this.code = code;
        this.type = type;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    /* 成功结果静态类 **/
    public static <T> Result<T> success() {
        return success(null, null);
    }

    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return instance(ResultType.MSG_SUCCESS, null, msg, data);
    }

    /* 失败结果静态类 **/
    public static <T> Result<T> fail(String msg) {
        return instance(ResultType.MSG_ERROR, null, msg, null);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return instance(code, null, msg, null);
    }

    public static <T> Result<T> fail(int code, String msg, T data) {
        return instance(code, null, msg, data);
    }

    private static <T> Result<T> instance(Integer code, String type, String msg, T data) {
        return new Result<>(code, type, msg, data);
    }

    /**
     * 要求业务处理成功
     *
     * @return
     */
    public boolean isSuccess() {
        return ResultType.MSG_SUCCESS.equals(this.code);
    }

    /**
     * 要求业务处理成功，且数据不为空
     *
     * @return
     */
    public boolean isData() {
        return isSuccess() && null != data;
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }
}
