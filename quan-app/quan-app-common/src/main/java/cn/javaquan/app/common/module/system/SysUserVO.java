package cn.javaquan.app.common.module.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class SysUserVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    private SysUserInfoVO info;

    private SysUserAccountVO account;

    private SysUserTripartiteAccountVO tripartiteAccount;

}
