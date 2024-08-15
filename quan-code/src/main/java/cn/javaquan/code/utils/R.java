package cn.javaquan.code.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author chenshun/sunlightcs@gmail.com
 * @since 1.0.0
 */
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 默认的构造方法，初始化code=0.
     */
    public R() {
        put("code", 0);
    }

    /**
     * 错误返回.
     * @return R
     */
    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    /**
     * 错误返回.
     * @param msg 错误信息
     * @return R
     */
    public static R error(String msg) {
        return error(500, msg);
    }

    /**
     * 错误返回.
     * @param code 错误码
     * @param msg 错误信息
     * @return R
     */
    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * 成功返回.
     * @param msg 消息
     * @return R
     */
    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    /**
     * 成功返回.
     * @param map 数据
     * @return R
     */
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    /**
     * 成功返回.
     * @return R
     */
    public static R ok() {
        return new R();
    }

    /**
     * 自定义响应数据.
     * @param key 键
     * @param value 值
     * @return R
     */
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
