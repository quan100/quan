package cn.javaquan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 第三方账号绑定事件参数.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class BoundEvent implements Serializable {

    private static final long serialVersionUID = -4351298586329008624L;

    /**
     * 访问凭证.
     */
    @NotBlank(message = "访问凭证不可为空")
    private String authId;

}
