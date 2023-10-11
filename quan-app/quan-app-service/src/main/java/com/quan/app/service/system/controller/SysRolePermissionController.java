package com.quan.app.service.system.controller;

import com.quan.app.common.module.system.SysRolePermissionAddCommand;
import com.quan.app.common.module.system.SysRolePermissionDTO;
import com.quan.app.common.module.system.SysRolePermissionQuery;
import com.quan.app.common.module.system.SysRolePermissionUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.service.SysRolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 角色权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/role/permission/")
public class SysRolePermissionController {

    private final SysRolePermissionService sysRolePermissionService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysRolePermissionDTO>> page(SysRolePermissionQuery query) {
        return sysRolePermissionService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysRolePermissionDTO> details(@RequestParam Long id) {
        return sysRolePermissionService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysRolePermissionUpdateCommand cmd) {
        return sysRolePermissionService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysRolePermissionAddCommand cmd) {
        return sysRolePermissionService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysRolePermissionAddCommand> cmds) {
        return sysRolePermissionService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysRolePermissionService.deleteByIds(ids);
    }

    /**
     * 获取角色对应的权限ID
     *
     * @param roleId 角色ID
     * @return permissionId列表
     */
    @GetMapping("rolePermissionIds")
    public Result<List<Long>> rolePermissionIds(@RequestParam(value = "roleId") Long roleId) {
        return sysRolePermissionService.rolePermissionIds(roleId);
    }

}
