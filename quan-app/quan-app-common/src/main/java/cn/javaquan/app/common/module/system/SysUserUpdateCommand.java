package cn.javaquan.app.common.module.system;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class SysUserUpdateCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 用户信息.
     */
    @NotNull(message = "用户信息不能为空")
    @Valid
    private SysUserInfoUpdateCommand info;

    /**
     * 账号信息.
     */
    @Valid
    private SysUserAccountUpdateCommand account;

    /**
     * 用户第三方账户.
     */
    @Valid
    private SysUserTripartiteAccountUpdateCommand tripartiteAccount;

}
