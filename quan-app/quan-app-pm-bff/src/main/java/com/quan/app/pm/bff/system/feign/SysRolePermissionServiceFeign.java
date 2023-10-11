package com.quan.app.pm.bff.system.feign;

import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.common.module.system.SysRolePermissionAddCommand;
import com.quan.app.common.module.system.SysRolePermissionDTO;
import com.quan.app.common.module.system.SysRolePermissionQuery;
import com.quan.app.common.module.system.SysRolePermissionUpdateCommand;
import com.quan.app.pm.bff.system.feign.fallback.SysRolePermissionServiceFallback;
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
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = SysRolePermissionServiceFallback.class)
public interface SysRolePermissionServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/role/permission/page")
    Result<PageResult<SysRolePermissionDTO>> page(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/sys/role/permission/details")
    Result<SysRolePermissionDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/sys/role/permission/update")
    Result<Boolean> update(@RequestBody SysRolePermissionUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/sys/role/permission/save")
    Result<Boolean> save(@RequestBody SysRolePermissionAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/sys/role/permission/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysRolePermissionAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/sys/role/permission/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取角色对应的权限ID
     *
     * @param roleId 角色ID主键
     * @return 权限ID列表
     */
    @GetMapping("/service/sys/role/permission/rolePermissionIds")
    Result<List<Long>> rolePermissionIds(@RequestParam(value = "roleId") Long roleId);

}
