package cn.javaquan.app.common.module.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class SysUserVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 用户信息.
     */
    private SysUserInfoVO info;

    /**
     * 用户账号.
     */
    private SysUserAccountVO account;

    /**
     * 用户第三方账户.
     */
    private SysUserTripartiteAccountVO tripartiteAccount;

}
