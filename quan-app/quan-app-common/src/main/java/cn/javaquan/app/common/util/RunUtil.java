package cn.javaquan.app.common.util;

import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.util.function.Run;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 提供函数式运行工具类.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Slf4j
public final class RunUtil {

    /**
     * 私有构造器.
     */
    private RunUtil() {
    }

    /**
     * 当{@code doRun}校验通过后执行函数，否则不进行任何操作.
     * @param doRun 是否执行
     * @param func 执行的函数
     */
    public static void doRun(boolean doRun, Run func) {
        doRun(doRun, true, func);
    }

    /**
     * 当{@code doRun}校验通过后执行函数，否则不进行任何操作.
     * @param doRun 是否执行
     * @param isThrow 当捕获 {@code func} 运行异常时，是否抛出该异常
     * @param func 执行的函数
     */
    public static void doRun(boolean doRun, boolean isThrow, Run func) {
        if (doRun) {
            try {
                func.apply();
            }
            catch (Exception ex) {
                log.error("运行异常", ex);
                Validate.isFalse(isThrow, ErrorCodeEnum.OPERATION_ERROR);
            }
        }
    }

    /**
     * 当{@code result}状态返回成功时执行函数，否则不进行任何操作.
     * @param result 当状态码为成功时执行函数
     * @param func 待执行的函数
     * @param <R> 约定的响应参数数据类型
     * @return 运行结果
     */
    public static <R> Result<R> doRun(Result<R> result, Run func) {
        if (result.isSuccess()) {
            func.apply();
        }
        return result;
    }

    /**
     * 当{@code result}状态返回成功时执行函数并返回该函数的值.
     * @param result 当状态码为成功时执行函数
     * @param func 待执行的函数
     * @param <T> 约定的响应参数数据类型
     * @return 运行结果
     */
    public static <T> Result<T> doRun(Result<?> result, Supplier<Result<T>> func) {
        if (result.isSuccess()) {
            return func.get();
        }
        return Result.fail(result.getCode(), result.getMessage(), result.getType());
    }

    /**
     * 当{@code doRun}校验通过后执行函数；当执行结果不为true时抛出异常.
     * @param doRun 是否执行
     * @param func 执行的函数
     * @param <R> 执行的函数返回参数约定的类型
     * @return 运行结果
     */
    public static <R extends Boolean> boolean doRun(boolean doRun, Supplier<R> func) {
        return doRun(doRun, true, func);
    }

    /**
     * 当{@code doRun}校验通过后执行函数；且当执行结果不为true时，根据{@code isThrow} 参数校验是否抛出异常.
     * @param doRun 是否执行
     * @param isThrow 返回失败结果时，是否抛出异常
     * @param func 执行的函数
     * @param <R> 约定的响应数据类型
     * @return 运行结果
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
     * 当{@code doRun}校验通过后执行函数，并返回函数的值；否则返回空.
     * @param doRun 是否执行
     * @param params 执行的函数传入参数
     * @param func 执行的函数
     * @param <T> 执行的函数传入参数约定的类型
     * @param <R> 执行的函数返回参数约定的类型
     * @return 运行结果
     */
    public static <T, R> R doRun(boolean doRun, T params, Function<T, R> func) {
        if (doRun) {
            return func.apply(params);
        }
        return null;
    }

}
