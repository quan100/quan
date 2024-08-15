package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.core.system.entity.SysUserAccountPO;
import cn.javaquan.app.core.system.repository.SysUserAccountRepository;
import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountDTO;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户账号.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/sys/user/account/")
public class SysUserAccountController {

    private final SysUserAccountRepository sysUserAccountRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysUserAccountPO>> page(SysUserAccountQuery query) {
        return Result.success(sysUserAccountRepository.page(query, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysUserAccountDTO> details(@RequestParam Long id) {
        return Result.success(sysUserAccountRepository.getUserAccountById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserAccountUpdateCommand cmd) {
        sysUserAccountRepository.updateUserAccount(cmd);
        return Result.success();
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserAccountAddCommand cmd) {
        sysUserAccountRepository.addUserAccount(cmd);
        return Result.success();
    }

    // /**
    // * 批量新增
    // *
    // * @param cmds
    // * @return
    // */
    // @PostMapping("saveBatch")
    // public Result<Boolean> saveBatch(@RequestBody List<SysUserAccountAddCommand> cmds)
    // {
    // List<SysUserAccountPO> pos = SysUserAccountAssembler.INSTANCE.toAddPOS(cmds);
    // return Result.success(sysUserAccountRepository.saveBatch(pos));
    // }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserAccountRepository.removeByIds(ids));
    }

    /**
     * 查询账号信息.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("userAccount")
    public Result<SysUserAccountPO> getUserAccount(SysUserAccountQuery query) {
        return Result.success(sysUserAccountRepository.getUserAccount(query));
    }

}
