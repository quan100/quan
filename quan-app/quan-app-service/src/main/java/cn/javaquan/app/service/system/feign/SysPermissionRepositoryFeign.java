package cn.javaquan.app.service.system.feign;

import cn.javaquan.app.common.module.system.SubsetPermissionsQuery;
import cn.javaquan.app.common.module.system.SysPermissionAddCommand;
import cn.javaquan.app.common.module.system.SysPermissionDTO;
import cn.javaquan.app.common.module.system.SysPermissionQuery;
import cn.javaquan.app.common.module.system.SysPermissionTreeDTO;
import cn.javaquan.app.common.module.system.SysPermissionUpdateCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.service.system.feign.fallback.SysPermissionRepositoryFallback;
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
 * 系统资源权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = SysPermissionRepositoryFallback.class)
public interface SysPermissionRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/sys/permission/page")
    Result<PageResult<SysPermissionDTO>> page(@SpringQueryMap SysPermissionQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/sys/permission/details")
    Result<SysPermissionDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/sys/permission/update")
    Result<Boolean> update(@RequestBody SysPermissionUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/sys/permission/save")
    Result<Boolean> save(@RequestBody SysPermissionAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/sys/permission/saveBatch")
    Result saveBatch(@RequestBody List<SysPermissionAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/sys/permission/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取角色有效权限列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/sys/permission/rolePermission")
    Result<List<SysPermissionDTO>> getRolePermission(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 子权限查询.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/sys/permission/subsetPermissions")
    Result<PageResult<SysPermissionTreeDTO>> getSubsetPermissions(@SpringQueryMap SubsetPermissionsQuery query);

    /**
     * 获取树形结构权限.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/sys/permission/treePermissions")
    Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(@SpringQueryMap SubsetPermissionsQuery query);

    /**
     * 获取权限列表，提供网关权限配置.
     * @return 权限列表
     */
    @GetMapping("/core/sys/permission/perms")
    Result<List<SysPermissionDTO>> getPerms();

}
