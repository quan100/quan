package cn.javaquan.security.common.dto.response;

import cn.javaquan.security.common.dto.AccessorInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限认证响应.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Getter
@Setter
public class AuthenticateResponse {

    /**
     * 是否继续执行下一步,true：是，false：否.
     */
    private boolean execute;

    /**
     * token令牌.
     */
    private String token = "";

    /**
     * 返回的信息.
     */
    private AccessorInfo info;

}
