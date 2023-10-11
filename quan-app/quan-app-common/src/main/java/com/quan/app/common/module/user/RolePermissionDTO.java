package com.quan.app.common.module.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangquan
 */
@Data
public class RolePermissionDTO implements Serializable {
    private static final long serialVersionUID = -3711235552399876974L;

    /**
     *
     */
    private Long id;

    /**
     * 上级ID
     */
    private Long parentId;

    private Long roleId;

    /**
     * 权限ID
     */
    private String permissionId;

    /**
     * 权限动作
     */
    private String action;

    /**
     * 权限访问路径
     */
    private String path;

}
