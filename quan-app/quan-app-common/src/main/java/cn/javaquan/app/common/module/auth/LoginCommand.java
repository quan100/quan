package cn.javaquan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录命令参数.
 *
 * @author quan.wang
 * @since 1.0.0
 */
@Data
public class LoginCommand implements Serializable {

    private static final long serialVersionUID = -3645277665720861695L;

    /**
     * 登录账号.
     */
    @NotBlank(message = "账号不可为空")
    private String account;

    /**
     * 登录密码.
     */
    @NotBlank(message = "密码不可为空")
    private String password;

    /**
     * 验证码.
     */
    @NotBlank(message = "验证码不可为空")
    private String captcha;

    /**
     * 会话ID.
     */
    private String sessionId;

    /**
     * 登录IP.
     */
    private String ip;

}
