package com.quan.app.common.module.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wangquan
 * @date 2020/4/16 22:12
 */
@Data
public class UserInfoQuery {

    /**
     * token
     */
    private transient String token;

    @NotBlank(message = "userId不可为空")
    private String userId;
}
