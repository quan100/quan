package com.quan.app.service.system.service;

import com.quan.app.common.module.system.SysRolePermissionAddCommand;
import com.quan.app.common.module.system.SysRolePermissionDTO;
import com.quan.app.common.module.system.SysRolePermissionQuery;
import com.quan.app.common.module.system.SysRolePermissionUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.feign.SysRolePermissionRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@Component
public class SysRolePermissionService {

    private final SysRolePermissionRepositoryFeign sysRolePermissionRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<SysRolePermissionDTO>> page(SysRolePermissionQuery query) {
        return sysRolePermissionRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<SysRolePermissionDTO> details(Long id) {
        return sysRolePermissionRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(SysRolePermissionUpdateCommand cmd) {
        return sysRolePermissionRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(SysRolePermissionAddCommand cmd) {
        return sysRolePermissionRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<SysRolePermissionAddCommand> cmds) {
        return sysRolePermissionRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysRolePermissionRepositoryFeign.deleteByIds(ids);
    }

    public Result<List<Long>> rolePermissionIds(Long roleId) {
        SysRolePermissionQuery query = new SysRolePermissionQuery();
        query.setRoleId(roleId);
        Result<List<Long>> permissionIdList = sysRolePermissionRepositoryFeign.getRolePermissionIds(query);
        return permissionIdList;
    }
}
