package cn.javaquan.app.common.exception.handler;

import cn.javaquan.app.common.exception.SystemException;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.notify.SystemNotifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器.
 * <p>
 * 将异常信息转换为统一的响应参数格式: {@link Result}
 *
 * @author wangquan
 * @since 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class SystemExceptionHandler {

    /**
     * 自定义异常处理.
     * @param ex 自定义异常
     * @return 统一响应参数
     */
    @ExceptionHandler(SystemException.class)
    public Result<?> handlerSystemException(SystemException ex) {
        log.error(ex.getMessage(), ex);
        return Result.fail(ex.getCode(), ex.getMsg());
    }

    /**
     * 路径不存在.
     * @param ex 路径不存在抛出的异常
     * @return 统一响应参数
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handlerNoFoundException(NoHandlerFoundException ex) {
        log.error(ex.getMessage(), ex);
        return Result.fail(HttpStatus.NOT_FOUND.value(), "路径不存在，请检查路径是否正确");
    }

    /**
     * 参数验证不通过的异常处理.
     * @param ex 当对带有@Valid注释的参数的验证失败时抛出的异常
     * @return 统一响应参数
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return Result.fail(ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 未通过 {@link javax.validation.Valid} 验证的异常处理.
     * @param ex 参数验证不通过的异常
     * @return 统一响应参数
     */
    @ExceptionHandler(BindException.class)
    public Result<?> handleBindException(BindException ex) {
        return Result.fail(ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 全局异常处理.
     * @param ex 全局异常
     * @return 统一响应参数
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    /**
     * 系统通知异常.
     * @param ex 系统通知异常
     * @return 统一响应参数
     */
    @ExceptionHandler(SystemNotifyException.class)
    public Result<?> handleFeignException(SystemNotifyException ex) {
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    /**
     * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException.
     * @param ex 当上传超过允许的最大上传大小时抛出。
     * @return 统一响应参数
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        log.error(ex.getMessage(), ex);
        return Result.fail(HttpStatus.PAYLOAD_TOO_LARGE.value(), "文件大小超出10MB限制, 请压缩或降低文件质量! ");
    }

}
