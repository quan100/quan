package com.quan.app.common.module.system;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户账号
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class SysUserAccountAddCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 用户ID
     * <p>
     * 自动生成
     */
    private transient String userId;

    /**
     * 登录密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 登录类型（1：普通用户；2：会员用户）
     */
    private Integer type;

    /**
     * 状态（0：正常，1：冻结，2：注销）
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 应用编码，标识权限所属的应用
     */
    private String appType;

    /**
     * 角色ID
     */
    private List<Long> roleIdList;

}
