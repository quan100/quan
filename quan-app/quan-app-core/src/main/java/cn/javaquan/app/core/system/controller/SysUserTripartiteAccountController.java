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
 * 用户第三方账户.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/sys/user/tripartite/account/")
public class SysUserTripartiteAccountController {

    private final SysUserTripartiteAccountRepository sysUserTripartiteAccountRepository;

    private final SysUserAccountRepository sysUserAccountRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysUserTripartiteAccountPO>> page(SysUserTripartiteAccountQuery query) {
        SysUserTripartiteAccountPO po = SysUserTripartiteAccountAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysUserTripartiteAccountRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysUserTripartiteAccountPO> details(@RequestParam Long id) {
        return Result.success(sysUserTripartiteAccountRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserTripartiteAccountUpdateCommand cmd) {
        sysUserTripartiteAccountRepository.updateTripartiteAccount(cmd);
        return Result.success();
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserTripartiteAccountAddCommand cmd) {
        sysUserTripartiteAccountRepository.addTripartiteAccount(cmd);
        return Result.success();
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserTripartiteAccountAddCommand> cmds) {
        List<SysUserTripartiteAccountPO> pos = SysUserTripartiteAccountAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysUserTripartiteAccountRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserTripartiteAccountRepository.removeByIds(ids));
    }

    /**
     * 根据邮箱查询绑定信息.
     * @param email 邮箱
     * @return 查询结果
     */
    @GetMapping("getByEmail")
    public Result<SysUserTripartiteAccountPO> getByEmail(@RequestParam String email) {
        return Result.success(sysUserTripartiteAccountRepository.getByEmail(email));
    }

    /**
     * 根据邮箱查询绑定信息.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("getByTripartite")
    public Result<SysUserTripartiteAccountPO> getByTripartite(SysUserTripartiteAccountQuery query) {
        return Result.success(sysUserTripartiteAccountRepository.getByTripartite(query));
    }

}
