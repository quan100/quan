package cn.javaquan.app.common.module.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限关联.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class PermissionRoleDTO implements Serializable {

    private static final long serialVersionUID = 2300246404797295777L;

    /**
     * 角色ID.
     */
    private Long roleId;

    /**
     * 角色编码.
     */
    private String code;

    /**
     * 权限ID.
     */
    private Long permissionId;

}
