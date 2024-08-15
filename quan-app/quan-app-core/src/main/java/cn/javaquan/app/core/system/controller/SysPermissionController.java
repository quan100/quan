package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.common.module.system.SubsetPermissionsQuery;
import cn.javaquan.app.common.module.system.SysPermissionAddCommand;
import cn.javaquan.app.common.module.system.SysPermissionQuery;
import cn.javaquan.app.common.module.system.SysPermissionTreeDTO;
import cn.javaquan.app.common.module.system.SysPermissionUpdateCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.core.system.convert.SysPermissionAssembler;
import cn.javaquan.app.core.system.entity.SysPermissionPO;
import cn.javaquan.app.core.system.repository.SysPermissionRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
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
 * 系统资源权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/sys/permission/")
public class SysPermissionController {

    private final SysPermissionRepository sysPermissionRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysPermissionPO>> page(SysPermissionQuery query) {
        SysPermissionPO po = SysPermissionAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysPermissionRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysPermissionPO> details(@RequestParam Long id) {
        return Result.success(sysPermissionRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysPermissionUpdateCommand cmd) {
        SysPermissionPO po = SysPermissionAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysPermissionRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysPermissionAddCommand cmd) {
        SysPermissionPO po = SysPermissionAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysPermissionRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysPermissionAddCommand> cmds) {
        List<SysPermissionPO> pos = SysPermissionAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysPermissionRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysPermissionRepository.removeByIds(ids));
    }

    /**
     * 获取角色有效权限列表.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("rolePermission")
    public Result<List<SysPermissionPO>> getRolePermission(SysRolePermissionQuery query) {
        return Result.success(sysPermissionRepository.getRolePermission(query));
    }

    /**
     * 子权限查询.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("subsetPermissions")
    public Result<PageResult<SysPermissionPO>> getSubsetPermissions(SubsetPermissionsQuery query) {
        return Result.success(sysPermissionRepository.getSubsetPermissions(query));
    }

    /**
     * 获取树形结构权限.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("treePermissions")
    public Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(SubsetPermissionsQuery query) {
        return Result.success(sysPermissionRepository.getTreePermissions(query));
    }

    /**
     * 获取权限列表，提供网关权限配置.
     * <p>
     * 仅获取按钮权限，不匹配菜单权限（菜单权限由路由控制）
     * @return 权限列表
     */
    @GetMapping("perms")
    public Result<List<SysPermissionPO>> getPerms() {
        return Result.success(sysPermissionRepository.getPerms());
    }

}
