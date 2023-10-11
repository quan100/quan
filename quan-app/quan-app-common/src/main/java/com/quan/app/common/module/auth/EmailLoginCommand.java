package com.quan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author quan.wang
 * @date 2020/1/13 14:34
 */
@Data
public class EmailLoginCommand implements Serializable {
    private static final long serialVersionUID = -3645277665720861695L;

    /**
     * 登录邮箱
     */
    @NotBlank(message = "邮箱不可为空")
    private String email;

    /**
     * 邮箱登录申请的动态密码
     */
    @NotBlank(message = "动态密码不可为空")
    private String password;

    /**
     * 验证码
     */
    private String captcha;

    private String sessionId;

}
