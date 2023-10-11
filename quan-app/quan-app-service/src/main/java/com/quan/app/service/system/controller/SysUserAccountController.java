package com.quan.app.service.system.controller;

import com.quan.app.common.module.auth.AuthQuery;
import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.*;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.service.SysUserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户账号
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/user/account/")
public class SysUserAccountController {

    private final SysUserAccountService sysUserAccountService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysUserAccountDTO>> page(SysUserAccountQuery query) {
        return sysUserAccountService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysUserAccountDTO> details(@RequestParam Long id) {
        return sysUserAccountService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserAccountUpdateCommand cmd) {
        return sysUserAccountService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserAccountAddCommand cmd) {
        return sysUserAccountService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserAccountAddCommand> cmds) {
        return sysUserAccountService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserAccountService.deleteByIds(ids);
    }

    /**
     * 获取用户权限
     *
     * @return
     */
    @GetMapping(value = "userPermission")
    public Result<List<UserPermissionTreeDTO>> getUserPermission(AuthQuery query) {
        return sysUserAccountService.getUserPermission(query);
    }

    /**
     * 查询账号信息
     *
     * @param query
     * @return
     */
    @GetMapping("userAccount")
    public Result<SysUserAccountDTO> getUserAccount(SysUserAccountQuery query) {
        return sysUserAccountService.getUserAccount(query);
    }

}
