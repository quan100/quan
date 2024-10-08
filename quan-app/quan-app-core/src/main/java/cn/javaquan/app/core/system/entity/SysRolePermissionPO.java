package cn.javaquan.app.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_permission")
public class SysRolePermissionPO extends Model<SysRolePermissionPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID.
     */
    private Long roleId;

    /**
     * 权限ID.
     */
    private Long permissionId;

}
