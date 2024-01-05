package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.core.system.repository.SysRolePermissionRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.system.SysRolePermissionAddCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.module.system.SysRolePermissionUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.system.convert.SysRolePermissionAssembler;
import cn.javaquan.app.core.system.entity.SysRolePermissionPO;
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
@RequestMapping("/core/sys/role/permission/")
public class SysRolePermissionController {

    private final SysRolePermissionRepository sysRolePermissionRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(SysRolePermissionQuery query) {
        SysRolePermissionPO po = SysRolePermissionAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysRolePermissionRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(sysRolePermissionRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody SysRolePermissionUpdateCommand cmd) {
        SysRolePermissionPO po = SysRolePermissionAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysRolePermissionRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysRolePermissionAddCommand cmd) {
        SysRolePermissionPO po = SysRolePermissionAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysRolePermissionRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<SysRolePermissionAddCommand> cmds) {
        List<SysRolePermissionPO> pos = SysRolePermissionAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysRolePermissionRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysRolePermissionRepository.removeByIds(ids));
    }

    /**
     * 获取角色配置的权限ID
     *
     * @param query
     * @return
     */
    @GetMapping("rolePermissionIds")
    public Result<List<Long>> getRolePermissionIds(SysRolePermissionQuery query) {
        return Result.success(sysRolePermissionRepository.getRolePermissionIds(query));
    }

    /**
     * 获取角色权限数量
     *
     * @param query
     * @return
     */
    @GetMapping("count")
    public Result count(SysRolePermissionQuery query) {
        return Result.success(sysRolePermissionRepository.count(query));
    }

    /**
     * 删除角色权限
     *
     * @param event
     * @return
     */
    @DeleteMapping("rolePermission")
    public Result delRolePermission(@RequestBody SysRolePermissionEvent event) {
        return Result.success(sysRolePermissionRepository.delRolePermission(event));
    }

    /**
     * 获取角色配置的权限列表
     *
     * @return
     */
    @GetMapping("permissionRoles")
    public Result getPermissionRoles() {
        return Result.success(sysRolePermissionRepository.getPermissionRoles());
    }

}
