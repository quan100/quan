package cn.javaquan.app.pm.bff.system.feign;

import cn.javaquan.app.common.module.system.*;
import cn.javaquan.app.pm.bff.system.feign.fallback.SysPermissionServiceFallback;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
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
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = SysPermissionServiceFallback.class)
public interface SysPermissionServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/permission/page")
    Result<PageResult<SysPermissionDTO>> page(@SpringQueryMap SysPermissionQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/sys/permission/details")
    Result<SysPermissionDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/sys/permission/update")
    Result<Boolean> update(@RequestBody SysPermissionUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/sys/permission/save")
    Result<Boolean> save(@RequestBody SysPermissionAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/sys/permission/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysPermissionAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/sys/permission/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取子权限
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/permission/subsetPermissions")
    Result<PageResult<SysPermissionTreeDTO>> getSubsetPermissions(@SpringQueryMap SubsetPermissionsQuery query);

    /**
     * 获取树形结构权限
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/permission/treePermissions")
    Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(@SpringQueryMap SubsetPermissionsQuery query);
}
