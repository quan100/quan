package com.quan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author wangquan
 */
@Data
public class BoundEvent implements Serializable {

    private static final long serialVersionUID = -4351298586329008624L;

    @NotBlank(message = "访问凭证不可为空")
    private String authId;

}
