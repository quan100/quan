package com.quan.app.pm.bff.system.feign;

import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.*;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.feign.fallback.SysRoleServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = SysRoleServiceFallback.class)
public interface SysRoleServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/role/page")
    Result<PageResult<SysRoleDTO>> page(@SpringQueryMap SysRoleQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/sys/role/details")
    Result<SysRoleDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/sys/role/update")
    Result<Boolean> update(@RequestBody SysRoleUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/sys/role/save")
    Result<Boolean> save(@RequestBody SysRoleAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/sys/role/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysRoleAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/sys/role/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 角色授权
     *
     * @param event
     * @return
     */
    @PutMapping("/service/sys/role/authorizeRolePermission")
    void authorizeRolePermission(@RequestBody AuthorizeRolePermissionEvent event);
}
