package com.quan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户第三方账户
 *
 * @author wangquan
 * @date 2022/1/17 15:09
 */
@Data
public class TripartiteAuthEvent implements Serializable {

    private static final long serialVersionUID = 9129615075082380313L;

    /**
     * 第三方授权码
     */
    @NotBlank(message = "第三方授权码不能为空")
    private String authCode;

    private String code;

    private String state;

}
