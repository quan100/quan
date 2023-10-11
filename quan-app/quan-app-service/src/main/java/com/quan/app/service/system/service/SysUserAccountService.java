package com.quan.app.service.system.service;

import com.quan.app.common.module.auth.AuthQuery;
import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.*;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.feign.SysUserAccountRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户账号
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@Component
public class SysUserAccountService {

    private final SysUserAccountRepositoryFeign sysUserAccountRepositoryFeign;
    private final PermissionService permissionService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<SysUserAccountDTO>> page(SysUserAccountQuery query) {
        return sysUserAccountRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<SysUserAccountDTO> details(Long id) {
        return sysUserAccountRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(SysUserAccountUpdateCommand cmd) {
        return sysUserAccountRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(SysUserAccountAddCommand cmd) {
        return sysUserAccountRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<SysUserAccountAddCommand> cmds) {
        return sysUserAccountRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysUserAccountRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取用户权限
     *
     * @return
     */
    public Result<List<UserPermissionTreeDTO>> getUserPermission(AuthQuery query) {
        return Result.success(permissionService.getUserPermission(query));
    }

    /**
     * 查询账号信息
     *
     * @param query
     * @return
     */
    public Result<SysUserAccountDTO> getUserAccount(SysUserAccountQuery query) {
        return sysUserAccountRepositoryFeign.getUserAccount(query);
    }
}
