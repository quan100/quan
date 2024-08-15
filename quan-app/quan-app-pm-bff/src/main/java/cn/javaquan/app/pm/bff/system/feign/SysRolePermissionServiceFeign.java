package cn.javaquan.app.pm.bff.system.feign;

import cn.javaquan.app.pm.bff.system.feign.fallback.SysRolePermissionServiceFallback;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.common.module.system.SysRolePermissionAddCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.module.system.SysRolePermissionUpdateCommand;
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
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = SysRolePermissionServiceFallback.class)
public interface SysRolePermissionServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/sys/role/permission/page")
    Result<PageResult<SysRolePermissionDTO>> page(@SpringQueryMap SysRolePermissionQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/sys/role/permission/details")
    Result<SysRolePermissionDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/sys/role/permission/update")
    Result<Boolean> update(@RequestBody SysRolePermissionUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/sys/role/permission/save")
    Result<Boolean> save(@RequestBody SysRolePermissionAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/sys/role/permission/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysRolePermissionAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/sys/role/permission/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取角色对应的权限ID.
     * @param roleId 角色ID主键
     * @return 权限ID列表
     */
    @GetMapping("/service/sys/role/permission/rolePermissionIds")
    Result<List<Long>> rolePermissionIds(@RequestParam Long roleId);

}
