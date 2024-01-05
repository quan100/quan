package cn.javaquan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author quan.wang
 * @date 2020/1/13 14:34
 */
@Data
public class EmailCodeCommand implements Serializable {
    private static final long serialVersionUID = -3645277665720861695L;

    /**
     * 登录邮箱
     */
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不可为空")
    private String email;

    /**
     * 验证码
     */
    private String captcha;

    private String sessionId;
}
