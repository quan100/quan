package cn.javaquan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author wangquan
 * @date 2020/4/12 19:30
 */
@Data
public class CheckVerifyCodeEvent implements Serializable {
    private static final long serialVersionUID = -7717745884043033656L;

    @NotBlank(message = "验证码不可为空")
    private String verifyCode;

    private String sessionId;
}
