package cn.javaquan.app.service.system.feign;

import cn.javaquan.app.common.module.system.*;
import cn.javaquan.app.service.system.feign.fallback.SysRolePermissionRepositoryFallback;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = SysRolePermissionRepositoryFallback.class)
public interface SysRolePermissionRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/role/permission/page")
    Result<PageResult<SysRolePermissionDTO>> page(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/sys/role/permission/details")
    Result<SysRolePermissionDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/sys/role/permission/update")
    Result<Boolean> update(@RequestBody SysRolePermissionUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/sys/role/permission/save")
    Result<Boolean> save(@RequestBody SysRolePermissionAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/sys/role/permission/saveBatch")
    Result saveBatch(@RequestBody List<SysRolePermissionAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/sys/role/permission/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取角色权限数据
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/role/permission/rolePermissionIds")
    Result<List<Long>> getRolePermissionIds(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 获取角色权限数量
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/role/permission/count")
    Result<Integer> count(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 删除角色权限
     *
     * @param event
     * @return
     */
    @DeleteMapping("/core/sys/role/permission/rolePermission")
    Result<Boolean> delRolePermission(@RequestBody SysRolePermissionEvent event);

    /**
     * 获取角色配置的权限列表
     *
     * @return
     */
    @GetMapping("/core/sys/role/permission/permissionRoles")
    Result<List<PermissionRoleDTO>> getPermissionRoles();
}
