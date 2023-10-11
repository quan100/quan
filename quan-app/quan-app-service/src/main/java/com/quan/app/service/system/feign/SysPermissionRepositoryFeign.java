package com.quan.app.service.system.feign;

import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.*;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.feign.fallback.SysPermissionRepositoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统资源权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = SysPermissionRepositoryFallback.class)
public interface SysPermissionRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/permission/page")
    Result<PageResult<SysPermissionDTO>> page(@SpringQueryMap SysPermissionQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/sys/permission/details")
    Result<SysPermissionDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/sys/permission/update")
    Result<Boolean> update(@RequestBody SysPermissionUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/sys/permission/save")
    Result<Boolean> save(@RequestBody SysPermissionAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/sys/permission/saveBatch")
    Result saveBatch(@RequestBody List<SysPermissionAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/sys/permission/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取角色有效权限列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/permission/rolePermission")
    Result<List<SysPermissionDTO>> getRolePermission(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 子权限查询
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/permission/subsetPermissions")
    Result<PageResult<SysPermissionTreeDTO>> getSubsetPermissions(@SpringQueryMap SubsetPermissionsQuery query);

    /**
     * 获取树形结构权限
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/permission/treePermissions")
    Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(@SpringQueryMap SubsetPermissionsQuery query);

    /**
     * 获取权限列表，提供网关权限配置
     *
     * @return
     */
    @GetMapping("/core/sys/permission/perms")
    Result<List<SysPermissionDTO>> getPerms();

}
