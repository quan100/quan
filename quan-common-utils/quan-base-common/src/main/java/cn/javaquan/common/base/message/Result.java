package cn.javaquan.common.base.message;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应参数.
 *
 * @author wangquan
 * @since 2.3.1
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 485088578586623310L;

    /**
     * 标记 JSON字符串中 {@link #uniformFormat} 参数的键值.
     */
    public static final String UNIFORM_FORMAT_KEY = "uniformFormat";

    /**
     * 响应码.
     */
    private Integer code;

    /**
     * 响应类型.
     */
    private String type;

    /**
     * 响应信息.
     */
    private String message;

    /**
     * 响应数据.
     */
    private T data;

    /**
     * 标记参数为统一的格式. 在统一参数处理器中，会对所有响应参数进行检查，若响应参数非统一的格式，则将响应参数转换为 {@link Result} 格式并返回.
     * <p>
     * 为了避免当前参数被重复转换，应该设置该参数的值为 {@code true}
     */
    private boolean uniformFormat = true;

    /**
     * 构造方法.
     * @param code 响应码
     * @param type 响应类型
     * @param message 响应信息
     * @param data 响应数据
     */
    public Result(Integer code, String type, String message, T data) {
        this.code = code;
        this.type = type;
        this.message = message;
        this.data = data;
    }

    /**
     * 默认的构造方法.
     */
    public Result() {
    }

    /**
     * 默认成功结果.
     * @param <T> 约定的数据类型
     * @return 成功结果
     */
    public static <T> Result<T> success() {
        return success(null, null);
    }

    /**
     * 成功结果.
     * @param data 数据
     * @param <T> 约定的数据类型
     * @return 成功结果
     */
    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    /**
     * 成功结果.
     * @param msg 消息
     * @param data 数据
     * @param <T> 约定的数据类型
     * @return 成功结果
     */
    public static <T> Result<T> success(String msg, T data) {
        return instance(ResultType.MSG_SUCCESS, null, msg, data);
    }

    /**
     * 默认失败结果.
     * @param msg 失败信息
     * @param <T> 约定的数据类型
     * @return 失败结果
     */
    public static <T> Result<T> fail(String msg) {
        return instance(ResultType.MSG_ERROR, null, msg, null);
    }

    /**
     * 失败结果.
     * @param resultType 失败的错误信息类型
     * @param <T> 约定的数据类型
     * @return 失败结果
     */
    public static <T> Result<T> fail(ResultType resultType) {
        return instance(resultType.getCode(), null, resultType.getMessage(), null);
    }

    /**
     * 默认失败结果.
     * @param code 响应码
     * @param msg 失败信息
     * @param <T> 约定的数据类型
     * @return 失败结果
     */
    public static <T> Result<T> fail(int code, String msg) {
        return instance(code, null, msg, null);
    }

    /**
     * 默认失败结果.
     * @param code 响应码
     * @param msg 失败信息
     * @param type 响应类型
     * @param <T> 约定的数据类型
     * @return 失败结果
     */
    public static <T> Result<T> fail(int code, String msg, String type) {
        return instance(code, type, msg, null);
    }

    /**
     * 默认失败结果.
     * @param code 响应码
     * @param msg 失败信息
     * @param data 数据
     * @param <T> 约定的数据类型
     * @return 失败结果
     */
    public static <T> Result<T> fail(int code, String msg, T data) {
        return instance(code, null, msg, data);
    }

    /**
     * 创建一个响应结果实例.
     * @param code 响应码
     * @param msg 响应信息
     * @param type 响应类型
     * @param data 数据
     * @param <T> 约定的响应数据类型
     * @return 结果
     */
    public static <T> Result<T> instance(Integer code, String type, String msg, T data) {
        return new Result<>(code, type, msg, data);
    }

    /**
     * 要求业务处理成功.
     * @return 当响应结果为成功时，返回 {@code true}
     */
    public boolean isSuccess() {
        return ResultType.MSG_SUCCESS.equals(this.code);
    }

    /**
     * 要求业务处理成功，且数据不为空.
     * @return 当响应结果为成功且数据不为空时，返回 {@code true}
     */
    public boolean isData() {
        return isSuccess() && null != this.data;
    }

    /**
     * 将响应结果转换为 JSON 字符串.
     * @return json 字符串
     */
    public String toJSONString() {
        return JSON.toJSONString(this);
    }

}
