package com.quan.app.pm.bff.system.feign;

import com.quan.app.common.module.system.SysUserRoleAddCommand;
import com.quan.app.common.module.system.SysUserRoleDTO;
import com.quan.app.common.module.system.SysUserRoleQuery;
import com.quan.app.common.module.system.SysUserRoleUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.feign.fallback.SysUserRoleServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = SysUserRoleServiceFallback.class)
public interface SysUserRoleServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/user/role/page")
    Result<PageResult<SysUserRoleDTO>> page(@SpringQueryMap SysUserRoleQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/sys/user/role/details")
    Result<SysUserRoleDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/sys/user/role/update")
    Result<Boolean> update(@RequestBody SysUserRoleUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/sys/user/role/save")
    Result<Boolean> save(@RequestBody SysUserRoleAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/sys/user/role/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysUserRoleAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/sys/user/role/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    @GetMapping("/service/sys/user/role/userRole")
    Result<List<SysUserRoleDTO>> getUserRole(@RequestParam(value = "userId") String userId);

    /**
     * 根据角色ID查询数量
     *
     * @param roleIds
     * @return
     */
    @GetMapping("/service/sys/user/role/count")
    Result<Integer> getCount(List<Long> roleIds);

    /**
     * 删除
     *
     * @param roleIds
     * @return
     */
    @DeleteMapping("/service/sys/user/role/delByRoleId")
    Result<Boolean> delByRoleId(@RequestBody List<Long> roleIds);

}
