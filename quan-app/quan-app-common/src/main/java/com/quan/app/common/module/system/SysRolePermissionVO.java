package com.quan.app.common.module.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限配置
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class SysRolePermissionVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;

}
