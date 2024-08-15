package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.common.module.system.PermissionRoleDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionAddCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.module.system.SysRolePermissionUpdateCommand;
import cn.javaquan.app.core.system.repository.SysRolePermissionRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.system.convert.SysRolePermissionAssembler;
import cn.javaquan.app.core.system.entity.SysRolePermissionPO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/sys/role/permission/")
public class SysRolePermissionController {

    private final SysRolePermissionRepository sysRolePermissionRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysRolePermissionPO>> page(SysRolePermissionQuery query) {
        SysRolePermissionPO po = SysRolePermissionAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysRolePermissionRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysRolePermissionPO> details(@RequestParam Long id) {
        return Result.success(sysRolePermissionRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysRolePermissionUpdateCommand cmd) {
        SysRolePermissionPO po = SysRolePermissionAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysRolePermissionRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysRolePermissionAddCommand cmd) {
        SysRolePermissionPO po = SysRolePermissionAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysRolePermissionRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysRolePermissionAddCommand> cmds) {
        List<SysRolePermissionPO> pos = SysRolePermissionAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysRolePermissionRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysRolePermissionRepository.removeByIds(ids));
    }

    /**
     * 获取角色配置的权限ID.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("rolePermissionIds")
    public Result<List<Long>> getRolePermissionIds(SysRolePermissionQuery query) {
        return Result.success(sysRolePermissionRepository.getRolePermissionIds(query));
    }

    /**
     * 获取角色权限数量.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("count")
    public Result<Integer> count(SysRolePermissionQuery query) {
        return Result.success(sysRolePermissionRepository.count(query));
    }

    /**
     * 删除角色权限.
     * @param event 删除角色权限事件参数
     * @return 操作是否成功
     */
    @DeleteMapping("rolePermission")
    public Result<Boolean> delRolePermission(@RequestBody SysRolePermissionEvent event) {
        return Result.success(sysRolePermissionRepository.delRolePermission(event));
    }

    /**
     * 获取角色配置的权限列表.
     * @return 权限列表
     */
    @GetMapping("permissionRoles")
    public Result<List<PermissionRoleDTO>> getPermissionRoles() {
        return Result.success(sysRolePermissionRepository.getPermissionRoles());
    }

}
