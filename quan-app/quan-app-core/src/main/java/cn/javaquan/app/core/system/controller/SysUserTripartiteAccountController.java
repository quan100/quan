package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.core.system.repository.SysUserAccountRepository;
import cn.javaquan.app.core.system.repository.SysUserTripartiteAccountRepository;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.system.convert.SysUserTripartiteAccountAssembler;
import cn.javaquan.app.core.system.entity.SysUserTripartiteAccountPO;
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
@RequestMapping("/core/sys/user/tripartite/account/")
public class SysUserTripartiteAccountController {

    private final SysUserTripartiteAccountRepository sysUserTripartiteAccountRepository;
    private final SysUserAccountRepository sysUserAccountRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(SysUserTripartiteAccountQuery query) {
        SysUserTripartiteAccountPO po = SysUserTripartiteAccountAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysUserTripartiteAccountRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(sysUserTripartiteAccountRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody SysUserTripartiteAccountUpdateCommand cmd) {
        sysUserTripartiteAccountRepository.updateTripartiteAccount(cmd);
        return Result.success();
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysUserTripartiteAccountAddCommand cmd) {
        sysUserTripartiteAccountRepository.addTripartiteAccount(cmd);
        return Result.success();
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<SysUserTripartiteAccountAddCommand> cmds) {
        List<SysUserTripartiteAccountPO> pos = SysUserTripartiteAccountAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysUserTripartiteAccountRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserTripartiteAccountRepository.removeByIds(ids));
    }

    /**
     * 根据邮箱查询绑定信息
     *
     * @param email
     * @return
     */
    @GetMapping("getByEmail")
    public Result getByEmail(@RequestParam String email) {
        return Result.success(sysUserTripartiteAccountRepository.getByEmail(email));
    }

    /**
     * 根据邮箱查询绑定信息
     *
     * @param query
     * @return
     */
    @GetMapping("getByTripartite")
    public Result getByTripartite(SysUserTripartiteAccountQuery query) {
        return Result.success(sysUserTripartiteAccountRepository.getByTripartite(query));
    }

}
