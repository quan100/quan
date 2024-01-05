package cn.javaquan.app.common.module.system;

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
public class SysUserUpdateCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    @NotNull(message = "用户信息不能为空")
    @Valid
    private SysUserInfoUpdateCommand info;

    @Valid
    private SysUserAccountUpdateCommand account;

    @Valid
    private SysUserTripartiteAccountUpdateCommand tripartiteAccount;

}
