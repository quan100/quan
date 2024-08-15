package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountDTO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.service.SysUserTripartiteAccountService;
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
@RequestMapping("/service/sys/user/tripartite/account/")
public class SysUserTripartiteAccountController {

    private final SysUserTripartiteAccountService sysUserTripartiteAccountService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysUserTripartiteAccountDTO>> page(SysUserTripartiteAccountQuery query) {
        return sysUserTripartiteAccountService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysUserTripartiteAccountDTO> details(@RequestParam Long id) {
        return sysUserTripartiteAccountService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserTripartiteAccountUpdateCommand cmd) {
        return sysUserTripartiteAccountService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserTripartiteAccountAddCommand cmd) {
        return sysUserTripartiteAccountService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserTripartiteAccountAddCommand> cmds) {
        return sysUserTripartiteAccountService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserTripartiteAccountService.deleteByIds(ids);
    }

    /**
     * 根据邮箱查询绑定信息.
     * @param email 邮箱
     * @return 第三方账号绑定信息
     */
    @GetMapping("getByEmail")
    public Result<SysUserTripartiteAccountDTO> getByEmail(@RequestParam String email) {
        return sysUserTripartiteAccountService.getByEmail(email);
    }

    /**
     * 根据查询条件查询绑定信息.
     * @param query 查询参数
     * @return 第三方账号绑定信息
     */
    @GetMapping("getByTripartite")
    public Result<SysUserTripartiteAccountDTO> getByTripartite(SysUserTripartiteAccountQuery query) {
        return sysUserTripartiteAccountService.getByTripartite(query);
    }

}
