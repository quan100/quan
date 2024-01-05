package cn.javaquan.app.common.module.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色
 *
 * @author wangquan
 * @since 2020-12-27 17:50:38
 */
@Data
public class UserRoleDTO implements Serializable {

    private static final long serialVersionUID = 2201488689796480730L;

    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色权限（按钮权限）
     */
    private List<RolePermissionDTO> permissions;
}
