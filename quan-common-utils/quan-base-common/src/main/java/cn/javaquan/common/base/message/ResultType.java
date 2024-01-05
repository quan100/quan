package cn.javaquan.common.base.message;

import org.springframework.http.HttpStatus;

/**
 * @author wangquan
 */
public class ResultType {
    public static final Integer MSG_SUCCESS = HttpStatus.OK.value();
    public static final Integer MSG_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();
}
