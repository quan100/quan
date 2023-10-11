package com.quan.app.common.util;

import com.quan.app.common.constant.ErrorCodeEnum;
import com.quan.app.common.util.function.Run;
import com.quan.common.base.message.Result;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author wangquan
 */
public class RunUtil {

    /**
     * 当{@code doRun}校验通过后执行函数，否则不进行任何操作
     *
     * @param doRun 是否执行
     * @param func  执行的函数
     */
    public static void doRun(boolean doRun, Run func) {
        if (doRun) {
            func.apply();
        }
    }

    /**
     * 当{@code result}状态返回成功时执行函数，否则不进行任何操作
     *
     * @param result 当状态码为成功时执行函数
     * @param func   待执行的函数
     * @return {@code result}
     */
    public static <R> Result<R> doRun(Result<R> result, Run func) {
        if (result.isSuccess()) {
            func.apply();
        }
        return result;
    }

    /**
     * 当{@code result}状态返回成功时执行函数并返回该函数的值
     *
     * @param result 当状态码为成功时执行函数
     * @param func   待执行的函数
     * @return result
     */
    public static Result doRun(Result result, Supplier<Result> func) {
        if (result.isSuccess()) {
            return func.get();
        }
        return result;
    }


    /**
     * 当{@code doRun}校验通过后执行函数；当执行结果不为true时抛出异常
     *
     * @param doRun
     * @param func
     * @param <R>
     * @return
     */
    public static <R extends Boolean> boolean doRun(boolean doRun, Supplier<R> func) {
        return doRun(doRun, true, func);
    }

    /**
     * 当{@code doRun}校验通过后执行函数；且当执行结果不为true时，根据{@code isThrow} 参数校验是否抛出异常
     *
     * @param doRun   是否执行
     * @param isThrow 返回失败结果时，是否抛出异常
     * @param func    执行的函数
     */
    public static <R extends Boolean> boolean doRun(boolean doRun, boolean isThrow, Supplier<R> func) {
        if (doRun) {
            doRun = func.get();
        }
        if (isThrow) {
            Validate.isTrue(doRun, ErrorCodeEnum.OPERATION_ERROR);
        }
        return doRun;
    }

    /**
     * 当{@code doRun}校验通过后执行函数，并返回函数的值；否则返回空
     *
     * @param doRun  是否执行
     * @param params 执行的函数传入参数
     * @param func   执行的函数
     * @param <T>    执行的函数传入参数约定的类型
     * @param <R>    执行的函数返回参数约定的类型
     * @return <R>
     */
    public static <T, R> R doRun(boolean doRun, T params, Function<T, R> func) {
        if (doRun) {
            return func.apply(params);
        }
        return null;
    }
}
