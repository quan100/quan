package com.quan.app.pm.bff.system.controller;

import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.*;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.convert.AuthEntityAssembler;
import com.quan.app.pm.bff.system.feign.SysUserAccountServiceFeign;
import com.quan.security.common.annotation.AuthUser;
import com.quan.security.common.dto.entity.AuthEntity;
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
@RequestMapping("/sys/user/account/")
public class SysUserAccountController {

    private final SysUserAccountServiceFeign sysUserAccountServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysUserAccountDTO>> page(SysUserAccountQuery query) {
        return sysUserAccountServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysUserAccountDTO> details(@RequestParam Long id) {
        return sysUserAccountServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserAccountUpdateCommand cmd) {
        return sysUserAccountServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserAccountAddCommand cmd) {
        return sysUserAccountServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserAccountAddCommand> cmds) {
        return sysUserAccountServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserAccountServiceFeign.deleteByIds(ids);
    }

    @GetMapping("userPermission")
    public Result<List<UserPermissionTreeDTO>> getUserPermission(@AuthUser AuthEntity authEntity) {
        return sysUserAccountServiceFeign.getUserPermission(AuthEntityAssembler.INSTANCE.toAuthQuery(authEntity));
    }

}
