package cn.javaquan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 邮箱验证码验证参数.
 *
 * @author quan.wang
 * @since 1.0.0
 */
@Data
public class EmailCodeCommand implements Serializable {

    private static final long serialVersionUID = -3645277665720861695L;

    /**
     * 登录邮箱.
     */
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不可为空")
    private String email;

    /**
     * 验证码.
     */
    private String captcha;

    /**
     * 会话ID.
     */
    private String sessionId;

}
