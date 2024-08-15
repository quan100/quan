package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.module.system.SysRolePermissionAddCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.module.system.SysRolePermissionUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.feign.SysRolePermissionRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class SysRolePermissionService {

    private final SysRolePermissionRepositoryFeign sysRolePermissionRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<SysRolePermissionDTO>> page(SysRolePermissionQuery query) {
        return sysRolePermissionRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<SysRolePermissionDTO> details(Long id) {
        return sysRolePermissionRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(SysRolePermissionUpdateCommand cmd) {
        return sysRolePermissionRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(SysRolePermissionAddCommand cmd) {
        return sysRolePermissionRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<SysRolePermissionAddCommand> cmds) {
        return sysRolePermissionRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysRolePermissionRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 根据角色ID查询权限ID列表.
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    public Result<List<Long>> rolePermissionIds(Long roleId) {
        SysRolePermissionQuery query = new SysRolePermissionQuery();
        query.setRoleId(roleId);
        Result<List<Long>> permissionIdList = sysRolePermissionRepositoryFeign.getRolePermissionIds(query);
        return permissionIdList;
    }

}
