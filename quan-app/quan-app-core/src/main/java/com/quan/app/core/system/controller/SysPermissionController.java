package com.quan.app.core.system.controller;

import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.common.module.system.*;
import com.quan.app.core.system.convert.SysPermissionAssembler;
import com.quan.app.core.system.entity.SysPermissionPO;
import com.quan.app.core.system.repository.SysPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 系统资源权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/sys/permission/")
public class SysPermissionController {

    private final SysPermissionRepository sysPermissionRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(SysPermissionQuery query) {
        SysPermissionPO po = SysPermissionAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysPermissionRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(sysPermissionRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody SysPermissionUpdateCommand cmd) {
        SysPermissionPO po = SysPermissionAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysPermissionRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysPermissionAddCommand cmd) {
        SysPermissionPO po = SysPermissionAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysPermissionRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<SysPermissionAddCommand> cmds) {
        List<SysPermissionPO> pos = SysPermissionAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysPermissionRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysPermissionRepository.removeByIds(ids));
    }

    /**
     * 获取角色有效权限列表
     *
     * @param query
     * @return
     */
    @GetMapping("rolePermission")
    public Result getRolePermission(SysRolePermissionQuery query) {
        return Result.success(sysPermissionRepository.getRolePermission(query));
    }

    /**
     * 子权限查询
     *
     * @param query
     * @return
     */
    @GetMapping("subsetPermissions")
    public Result getSubsetPermissions(SubsetPermissionsQuery query) {
        return Result.success(sysPermissionRepository.getSubsetPermissions(query));
    }

    /**
     * 获取树形结构权限
     *
     * @param query
     * @return
     */
    @GetMapping("treePermissions")
    public Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(SubsetPermissionsQuery query) {
        return Result.success(sysPermissionRepository.getTreePermissions(query));
    }

    /**
     * 获取权限列表，提供网关权限配置
     *
     * @return
     */
    @GetMapping("perms")
    public Result<List<SysPermissionPO>> getPerms() {
        return Result.success(sysPermissionRepository.getPerms());
    }

}
