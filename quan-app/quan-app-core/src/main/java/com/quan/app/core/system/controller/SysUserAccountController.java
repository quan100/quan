package com.quan.app.core.system.controller;

import com.quan.app.common.module.system.SysUserAccountAddCommand;
import com.quan.app.common.module.system.SysUserAccountDTO;
import com.quan.app.common.module.system.SysUserAccountQuery;
import com.quan.app.common.module.system.SysUserAccountUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.core.system.repository.SysUserAccountRepository;
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
@RequestMapping("/core/sys/user/account/")
public class SysUserAccountController {

    private final SysUserAccountRepository sysUserAccountRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(SysUserAccountQuery query) {
        return Result.success(sysUserAccountRepository.page(query, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysUserAccountDTO> details(@RequestParam Long id) {
        return Result.success(sysUserAccountRepository.getUserAccountById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody SysUserAccountUpdateCommand cmd) {
        sysUserAccountRepository.updateUserAccount(cmd);
        return Result.success();
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysUserAccountAddCommand cmd) {
        sysUserAccountRepository.addUserAccount(cmd);
        return Result.success();
    }

//    /**
//     * 批量新增
//     *
//     * @param cmds
//     * @return
//     */
//    @PostMapping("saveBatch")
//    public Result saveBatch(@RequestBody List<SysUserAccountAddCommand> cmds) {
//        List<SysUserAccountPO> pos = SysUserAccountAssembler.INSTANCE.toAddPOS(cmds);
//        return Result.success(sysUserAccountRepository.saveBatch(pos));
//    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserAccountRepository.removeByIds(ids));
    }

    /**
     * 查询账号信息
     *
     * @param query
     * @return
     */
    @GetMapping("userAccount")
    public Result getUserAccount(SysUserAccountQuery query) {
        return Result.success(sysUserAccountRepository.getUserAccount(query));
    }
}
