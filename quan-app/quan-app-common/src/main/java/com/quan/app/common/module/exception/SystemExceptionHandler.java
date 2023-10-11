package com.quan.app.common.module.exception;

import com.quan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 * <p>
 * 将异常信息转换为统一的响应参数格式: {@link Result}
 *
 * @author wangquan
 * @date 2021/12/29 01:35
 */
@RestControllerAdvice
@Slf4j
public class SystemExceptionHandler {

    /**
     * 自定义异常处理
     *
     * @param e
     * @return 统一响应参数
     */
    @ExceptionHandler(SystemException.class)
    public Result<?> handleRRException(SystemException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(HttpStatus.NOT_FOUND.value(), "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Result.fail(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 未通过 {@link javax.validation.Valid} 验证的异常处理
     *
     * @param e
     * @return 统一响应参数
     */
    @ExceptionHandler(BindException.class)
    public Result<?> handleBindException(BindException e) {
        return Result.fail(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error(e.getMessage(), e);
        return Result.fail(HttpStatus.PAYLOAD_TOO_LARGE.value(), "文件大小超出10MB限制, 请压缩或降低文件质量! ");
    }
}
