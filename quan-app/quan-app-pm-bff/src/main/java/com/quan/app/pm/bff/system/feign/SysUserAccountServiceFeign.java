package com.quan.app.pm.bff.system.feign;

import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.common.module.auth.AuthQuery;
import com.quan.app.common.module.system.*;
import com.quan.app.pm.bff.system.feign.fallback.SysUserAccountServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户账号
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = SysUserAccountServiceFallback.class)
public interface SysUserAccountServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/user/account/page")
    Result<PageResult<SysUserAccountDTO>> page(@SpringQueryMap SysUserAccountQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/sys/user/account/details")
    Result<SysUserAccountDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/sys/user/account/update")
    Result<Boolean> update(@RequestBody SysUserAccountUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/sys/user/account/save")
    Result<Boolean> save(@RequestBody SysUserAccountAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/sys/user/account/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysUserAccountAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/sys/user/account/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取用户权限
     *
     * @return
     */
    @GetMapping(value = "/service/sys/user/account/userPermission")
    Result<List<UserPermissionTreeDTO>> getUserPermission(@SpringQueryMap AuthQuery query);

    /**
     * 查询账号信息
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/user/account/userAccount")
    Result<SysUserAccountDTO> getUserAccount(@SpringQueryMap SysUserAccountQuery query);

}
