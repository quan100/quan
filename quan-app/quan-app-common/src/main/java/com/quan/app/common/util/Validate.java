package com.quan.app.common.util;

import com.quan.app.common.constant.ErrorCodeEnum;
import com.quan.app.common.module.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * 校验处理器
 *
 * @author wangquan
 * @date 2021/1/22 17:10
 */
public final class Validate {

    public Validate() {
    }

    /**
     * 要求表达式为true<br>
     * 当表达式不为ture时，抛出异常
     *
     * @param expression
     * @param message
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throwException(message);
        }
    }

    /**
     * 要求表达式为true<br>
     * 当表达式不为ture时，抛出异常
     *
     * @param expression
     * @param errorCode
     */
    public static void isTrue(boolean expression, ErrorCodeEnum errorCode) {
        if (!expression) {
            throwException(errorCode);
        }
    }

    /**
     * 要求表达式为false<br>
     * 当表达式不为false时，抛出异常
     *
     * @param expression
     * @param message
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throwException(message);
        }
    }

    /**
     * 要求表达式为false<br>
     * 当表达式不为false时，抛出异常
     *
     * @param expression
     * @param errorCode
     */
    public static void isFalse(boolean expression, ErrorCodeEnum errorCode) {
        if (expression) {
            throwException(errorCode);
        }
    }

    /**
     * Valid 验证
     *
     * @param bindingResult
     */
    public static void valid(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throwException(bindingResult.getFieldError().getDefaultMessage());
        }
    }

    /**
     * 业务异常
     *
     * @param errorCode
     */
    public static void throwException(ErrorCodeEnum errorCode) {
        throwException(errorCode.getCode(), errorCode.getMsg());
    }

    /**
     * 业务异常
     *
     * @param message
     */
    public static void throwException(String message) {
        throwException(ErrorCodeEnum.PARAM_ERROR.getCode(), message);
    }

    /**
     * 业务异常
     *
     * @param message
     */
    public static void throwException(ErrorCodeEnum errorCodeEnum, String message) {
        throwException(errorCodeEnum.getCode(), message);
    }

    /**
     * 业务异常
     *
     * @param code    错误编码
     * @param message 错误信息
     */
    public static void throwException(int code, String message) {
        throw new SystemException(code, message);
    }

    /**
     * 要求对象为空<br>
     * 当对象不为空时，抛出异常
     *
     * @param object
     * @param errorCode
     */
    public static void isNull(Object object, ErrorCodeEnum errorCode) {
        if (!isNull(object)) {
            throwException(errorCode);
        }
    }

    /**
     * 要求对象不为空<br>
     * 当对象为空时，抛出异常
     *
     * @param object
     * @param errorCode
     */
    public static void isNotNull(Object object, ErrorCodeEnum errorCode) {
        if (isNull(object)) {
            throwException(errorCode);
        }
    }

    /**
     * 对象是否不为空<br>
     *
     * @param object
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 对象是否为空<br>
     *
     * @param object
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 要求集合不可为空
     * 当集合为空时，抛出异常
     *
     * @param collection
     * @return
     */
    public static void isEmpty(Collection<?> collection, ErrorCodeEnum errorCode) {
        if (isEmpty(collection)) {
            throwException(errorCode);
        }
    }

    /**
     * 验证集合是否为空
     * 当集合为空时，返回true
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 验证数组是否为空
     * 当数组为空时，返回true
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Object... collection) {
        return collection == null || collection.length == 0;
    }

    /**
     * 验证集合是否为空
     * 当集合为不为空时，返回true
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 验证map是否为空<br>
     * 当map为空时，返回true
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 验证map是否为空<br>
     * 当map不为空时，返回true
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 验证数组是否为空
     * 当数组为不为空时，返回true
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Object... collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断字符串是否为空
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        return StringUtils.isBlank(cs);
    }

    /**
     * 判断字符串是否不为空
     *
     * @param cs
     * @return
     */
    public static boolean isNotBlank(CharSequence cs) {
        return StringUtils.isNotBlank(cs);
    }

    /**
     * 要求字符串不可为空，否则抛出异常
     *
     * @param cs
     * @return
     */
    public static void isNotBlank(CharSequence cs, ErrorCodeEnum ErrorCodeEnum) {
        if (isBlank(cs)) {
            throwException(ErrorCodeEnum);
        }
    }

    /**
     * 如果字符串为空，则返回空字符串
     *
     * @param val
     * @return
     */
    public static String defaultValue(String val) {
        return defaultValue(val, "");
    }

    /**
     * 如果值为空，则返回设定的返回值
     *
     * @param val
     * @param returnVal 返回值
     * @return
     */
    public static String defaultValue(String val, String returnVal) {
        if (isBlank(val)) {
            return returnVal;
        }
        return val;
    }

    /**
     * 如果值为空，则返回0
     *
     * @param val
     * @return
     */
    public static int defaultValue(Integer val) {
        if (isNull(val)) {
            return 0;
        }
        return val;
    }

    /**
     * 如果集合为空，则返回空集合
     *
     * @param collection
     * @return
     */
    public static <T> List<T> defaultValue(List<T> collection) {
        if (isNull(collection)) {
            return Collections.emptyList();
        }
        return collection;
    }

    /**
     * 如果值为空，则返回设定的返回值
     *
     * @param val
     * @param returnVal 返回值
     * @return
     */
    public static int defaultValue(Integer val, Integer returnVal) {
        if (isNull(val)) {
            return returnVal;
        }
        return val;
    }

    /**
     * 如果值为空，则返回设定的返回值
     *
     * @param val
     * @return
     */
    public static long defaultValue(Long val) {
        if (isNull(val)) {
            return 0L;
        }
        return val;
    }

    /**
     * 如果值为空，则返回设定的返回值
     *
     * @param val
     * @param returnVal 返回值
     * @return
     */
    public static long defaultValue(Long val, Long returnVal) {
        if (isNull(val)) {
            return returnVal;
        }
        return val;
    }

    /**
     * 如果值为空，则返回设定的返回值
     *
     * @param val
     * @return
     */
    public static boolean defaultValue(Boolean val) {
        if (isNull(val)) {
            return false;
        }
        return val;
    }

    /**
     * 如果值为空，则返回设定的返回值
     *
     * @param val
     * @return
     */
    public static <T> T defaultValue(T val, T returnVal) {
        if (isNull(val)) {
            return returnVal;
        }
        return val;
    }

    /**
     * 根据属性去重
     *
     * @param data         去重数据
     * @param keyExtractor 用于提取{@link Comparable}排序键的函数
     * @param <T>          要比较的元素类型
     * @param <U>          {@code Comparable}排序键的类型
     * @return
     */
    public static <T, U extends Comparable<? super U>> List<T> distinct(List<T> data, Function<? super T, ? extends U> keyExtractor) {
        Validate.isEmpty(data, ErrorCodeEnum.PARAM_ERROR);
        return data.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(keyExtractor))), ArrayList::new));
    }

    /**
     * 数据去重
     *
     * @param data 去重数据
     * @return
     */
    public static <T> List<T> distinct(List<T> data) {
        return data.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 判断target是否在rs数组里
     *
     * @param rs
     * @param target
     * @return
     */
    public static boolean isIn(Object[] rs, Object target) {
        int idx = inArray(rs, target);
        return idx > -1;
    }

    /**
     * 判断target在rs数组里的位置，找不到则返回-1
     *
     * @param rs
     * @param target
     * @return
     */
    public static int inArray(Object[] rs, Object target) {
        if (isEmpty(rs)) {
            return -1;
        }
        boolean f = false;
        for (int i = 0; i < rs.length; ++i) {
            Object o = rs[i];
            f = o != null && o.equals(target);
            if (f) {
                return i;
            }
        }

        return -1;
    }

}
