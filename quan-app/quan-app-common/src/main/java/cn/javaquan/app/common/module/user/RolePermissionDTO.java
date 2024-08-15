package cn.javaquan.app.common.module.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class RolePermissionDTO implements Serializable {

    private static final long serialVersionUID = -3711235552399876974L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 上级ID.
     */
    private Long parentId;

    /**
     * 角色ID.
     */
    private Long roleId;

    /**
     * 权限ID.
     */
    private String permissionId;

    /**
     * 权限动作.
     */
    private String action;

    /**
     * 权限访问路径.
     */
    private String path;

}
