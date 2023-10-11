package com.quan.app.service.system.controller;

import com.quan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import com.quan.app.common.module.system.SysUserTripartiteAccountDTO;
import com.quan.app.common.module.system.SysUserTripartiteAccountQuery;
import com.quan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.service.SysUserTripartiteAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户第三方账户
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/user/tripartite/account/")
public class SysUserTripartiteAccountController {

    private final SysUserTripartiteAccountService sysUserTripartiteAccountService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysUserTripartiteAccountDTO>> page(SysUserTripartiteAccountQuery query) {
        return sysUserTripartiteAccountService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysUserTripartiteAccountDTO> details(@RequestParam Long id) {
        return sysUserTripartiteAccountService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserTripartiteAccountUpdateCommand cmd) {
        return sysUserTripartiteAccountService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserTripartiteAccountAddCommand cmd) {
        return sysUserTripartiteAccountService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserTripartiteAccountAddCommand> cmds) {
        return sysUserTripartiteAccountService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserTripartiteAccountService.deleteByIds(ids);
    }

    /**
     * 根据邮箱查询绑定信息
     *
     * @param email
     * @return
     */
    @GetMapping("getByEmail")
    public Result<SysUserTripartiteAccountDTO> getByEmail(@RequestParam String email) {
        return sysUserTripartiteAccountService.getByEmail(email);
    }

    /**
     * 根据查询条件查询绑定信息
     *
     * @param query
     * @return
     */
    @GetMapping("getByTripartite")
    public Result<SysUserTripartiteAccountDTO> getByTripartite(SysUserTripartiteAccountQuery query) {
        return sysUserTripartiteAccountService.getByTripartite(query);
    }

}
