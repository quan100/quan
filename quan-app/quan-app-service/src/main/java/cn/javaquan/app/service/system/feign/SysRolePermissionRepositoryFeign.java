package cn.javaquan.app.service.system.feign;

import cn.javaquan.app.common.module.system.PermissionRoleDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionAddCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.module.system.SysRolePermissionUpdateCommand;
import cn.javaquan.app.service.system.feign.fallback.SysRolePermissionRepositoryFallback;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = SysRolePermissionRepositoryFallback.class)
public interface SysRolePermissionRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/sys/role/permission/page")
    Result<PageResult<SysRolePermissionDTO>> page(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/sys/role/permission/details")
    Result<SysRolePermissionDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/sys/role/permission/update")
    Result<Boolean> update(@RequestBody SysRolePermissionUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/sys/role/permission/save")
    Result<Boolean> save(@RequestBody SysRolePermissionAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/sys/role/permission/saveBatch")
    Result saveBatch(@RequestBody List<SysRolePermissionAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/sys/role/permission/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取角色权限数据.
     * @param query 查询参数
     * @return 权限id列表
     */
    @GetMapping("/core/sys/role/permission/rolePermissionIds")
    Result<List<Long>> getRolePermissionIds(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 获取角色权限数量.
     * @param query 查询参数
     * @return 权限数量
     */
    @GetMapping("/core/sys/role/permission/count")
    Result<Integer> count(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 删除角色权限.
     * @param event 删除权限事件参数
     * @return 操作是否成功
     */
    @DeleteMapping("/core/sys/role/permission/rolePermission")
    Result<Boolean> delRolePermission(@RequestBody SysRolePermissionEvent event);

    /**
     * 获取角色配置的权限列表.
     * @return 权限列表
     */
    @GetMapping("/core/sys/role/permission/permissionRoles")
    Result<List<PermissionRoleDTO>> getPermissionRoles();

}
