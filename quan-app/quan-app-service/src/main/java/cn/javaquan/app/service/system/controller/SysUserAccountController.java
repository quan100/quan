package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountDTO;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.app.service.system.service.SysUserAccountService;
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
@RequestMapping("/service/sys/user/account/")
public class SysUserAccountController {

    private final SysUserAccountService sysUserAccountService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysUserAccountDTO>> page(SysUserAccountQuery query) {
        return sysUserAccountService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysUserAccountDTO> details(@RequestParam Long id) {
        return sysUserAccountService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserAccountUpdateCommand cmd) {
        return sysUserAccountService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserAccountAddCommand cmd) {
        return sysUserAccountService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserAccountAddCommand> cmds) {
        return sysUserAccountService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserAccountService.deleteByIds(ids);
    }

    /**
     * 获取用户权限.
     * @param query 查询参数
     * @return 用户权限
     */
    @DeleteMapping("/userPermission")
    public Result<List<UserPermissionTreeDTO>> getUserPermission(AuthQuery query) {
        return sysUserAccountService.getUserPermission(query);
    }

    /**
     * 查询账号信息.
     * @param query 查询参数
     * @return 用户账号信息
     */
    @GetMapping("userAccount")
    public Result<SysUserAccountDTO> getUserAccount(SysUserAccountQuery query) {
        return sysUserAccountService.getUserAccount(query);
    }

}
