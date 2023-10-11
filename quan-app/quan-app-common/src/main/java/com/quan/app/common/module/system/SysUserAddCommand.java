package com.quan.app.common.module.system;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class SysUserAddCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    @NotNull(message = "用户信息不能为空")
    @Valid
    private SysUserInfoAddCommand info;

    @Valid
    private SysUserAccountAddCommand account;

    @Valid
    private SysUserTripartiteAccountAddCommand tripartiteAccount;

}
