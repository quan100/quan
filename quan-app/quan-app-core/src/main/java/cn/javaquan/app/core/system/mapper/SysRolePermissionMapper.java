package cn.javaquan.app.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.javaquan.app.common.module.system.PermissionRoleDTO;
import cn.javaquan.app.core.system.entity.SysRolePermissionPO;

import java.util.List;

/**
 * 角色权限配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermissionPO> {

    /**
     * 角色权限列表.
     * @return 角色权限列表
     */
    List<PermissionRoleDTO> permissionRoleList();

}
