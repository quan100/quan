package cn.javaquan.common.base.message;

import org.springframework.http.HttpStatus;

/**
 * 响应类型.
 *
 * @author wangquan
 * @since 1.0.0
 */
public final class ResultType {

    /**
     * 私有构造方法.
     */
    private ResultType() {
    }

    /**
     * 默认成功信息.
     */
    public static final Integer MSG_SUCCESS = HttpStatus.OK.value();

    /**
     * 默认失败信息.
     */
    public static final Integer MSG_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();

}
