package cn.javaquan.app.common.module.system;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class SysRolePermissionQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 角色ID.
     */
    private Long roleId;

    /**
     * 权限ID.
     */
    private Long permissionId;

    /**
     * 类型，0：一级菜单，1：菜单，2：按钮.
     */
    private Integer type;

    /**
     * 根据角色ID查询.
     */
    private List<Long> roleIds;

    /**
     * 权限ID.
     */
    private List<Long> permissionIds;

}
