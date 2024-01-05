package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.system.*;
import cn.javaquan.app.service.system.service.SysPermissionService;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("/service/sys/permission/")
public class SysPermissionController {

    private final SysPermissionService sysPermissionService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysPermissionDTO>> page(SysPermissionQuery query) {
        return sysPermissionService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysPermissionDTO> details(@RequestParam Long id) {
        return sysPermissionService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysPermissionUpdateCommand cmd) {
        return sysPermissionService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysPermissionAddCommand cmd) {
        return sysPermissionService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysPermissionAddCommand> cmds) {
        return sysPermissionService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysPermissionService.deleteByIds(ids);
    }

    /**
     * 子权限查询
     *
     * @param query
     * @return
     */
    @GetMapping("subsetPermissions")
    public Result getSubsetPermissions(SubsetPermissionsQuery query) {
        return sysPermissionService.getSubsetPermissions(query);
    }

    /**
     * 获取树形结构权限
     *
     * @param query
     * @return
     */
    @GetMapping("treePermissions")
    public Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(@Valid SubsetPermissionsQuery query) {
        return sysPermissionService.getTreePermissions(query);
    }

    /**
     * 获取系统权限配置
     * 基于路径匹配权限
     * <p>
     * 提供网关鉴权使用
     *
     * @param defaultAuth 当菜单资源未配置权限时，默认配置该参数作为菜单资源默认权限
     * @return
     */
    @GetMapping("perms")
    public Result<List<String>> getPerms(@RequestParam String defaultAuth) {
        return sysPermissionService.getPerms(defaultAuth);
    }

}
