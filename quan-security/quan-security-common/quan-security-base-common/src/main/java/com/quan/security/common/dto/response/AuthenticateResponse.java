package com.quan.security.common.dto.response;

import com.quan.security.common.dto.AccessorInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wangquan
 * @date 2020/3/10 00:03
 */
@Getter
@Setter
public class AuthenticateResponse {

    /**
     * 是否继续执行下一步,true：是，false：否
     */
    private boolean execute;

    /**
     * token令牌
     */
    private String token = "";

    /**
     * 返回的信息
     */
    private AccessorInfo info;
}
