package cn.javaquan.app.core.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.javaquan.app.common.module.system.PermissionRoleDTO;
import cn.javaquan.app.core.system.entity.SysRolePermissionPO;

import java.util.List;

/**
 * 角色权限配置
 *
 * @author wangquan
 * @version 1.0.0
 * @date 2020-12-27 17:50:38
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermissionPO> {

    List<PermissionRoleDTO> permissionRoleList();

}
