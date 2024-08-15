package cn.javaquan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 验证码校验事件参数.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class CheckVerifyCodeEvent implements Serializable {

    private static final long serialVersionUID = -7717745884043033656L;

    /**
     * 验证码.
     */
    @NotBlank(message = "验证码不可为空")
    private String verifyCode;

    /**
     * 会话ID.
     */
    private String sessionId;

}
