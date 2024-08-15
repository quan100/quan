package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.system.SubsetPermissionsQuery;
import cn.javaquan.app.common.module.system.SysPermissionAddCommand;
import cn.javaquan.app.common.module.system.SysPermissionDTO;
import cn.javaquan.app.common.module.system.SysPermissionQuery;
import cn.javaquan.app.common.module.system.SysPermissionTreeDTO;
import cn.javaquan.app.common.module.system.SysPermissionUpdateCommand;
import cn.javaquan.app.service.system.service.SysPermissionService;
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

import javax.validation.Valid;
import java.util.List;

/**
 * 系统资源权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/permission/")
public class SysPermissionController {

    private final SysPermissionService sysPermissionService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysPermissionDTO>> page(SysPermissionQuery query) {
        return sysPermissionService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysPermissionDTO> details(@RequestParam Long id) {
        return sysPermissionService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysPermissionUpdateCommand cmd) {
        return sysPermissionService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysPermissionAddCommand cmd) {
        return sysPermissionService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysPermissionAddCommand> cmds) {
        return sysPermissionService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysPermissionService.deleteByIds(ids);
    }

    /**
     * 子权限查询.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("subsetPermissions")
    public Result getSubsetPermissions(SubsetPermissionsQuery query) {
        return sysPermissionService.getSubsetPermissions(query);
    }

    /**
     * 获取树形结构权限.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("treePermissions")
    public Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(@Valid SubsetPermissionsQuery query) {
        return sysPermissionService.getTreePermissions(query);
    }

    /**
     * 获取系统权限配置 基于路径匹配权限.
     * <p>
     * 提供网关鉴权使用
     * @param defaultAuth 当菜单资源未配置权限时，默认配置该参数作为菜单资源默认权限
     * @return 系统权限配置
     */
    @GetMapping("perms")
    public Result<List<String>> getPerms(@RequestParam String defaultAuth) {
        return sysPermissionService.getPerms(defaultAuth);
    }

}
