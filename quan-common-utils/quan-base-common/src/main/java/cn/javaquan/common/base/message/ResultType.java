package cn.javaquan.common.base.message;

import org.springframework.http.HttpStatus;

/**
 * 响应类型接口.
 *
 * @author wangquan
 * @since 2.3.1
 */
public interface ResultType {

    /**
     * 默认成功状态码.
     */
    Integer MSG_SUCCESS = HttpStatus.OK.value();

    /**
     * 默认失败状态码.
     */
    Integer MSG_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();

    /**
     * 获取响应码.
     * @return 响应码
     */
    Integer getCode();

    /**
     * 获取响应信息.
     * @return 响应信息
     */
    String getMessage();

}
