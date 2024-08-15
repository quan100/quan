package cn.javaquan.app.common.module.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户信息查询参数.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class UserInfoQuery {

    /**
     * token.
     */
    private transient String token;

    @NotBlank(message = "userId不可为空")
    private String userId;

}
