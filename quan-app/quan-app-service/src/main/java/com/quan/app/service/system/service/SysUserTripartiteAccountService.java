package com.quan.app.service.system.service;

import com.quan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import com.quan.app.common.module.system.SysUserTripartiteAccountDTO;
import com.quan.app.common.module.system.SysUserTripartiteAccountQuery;
import com.quan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.feign.SysUserTripartiteAccountRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户第三方账户
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@Component
public class SysUserTripartiteAccountService {

    private final SysUserTripartiteAccountRepositoryFeign sysUserTripartiteAccountRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<SysUserTripartiteAccountDTO>> page(SysUserTripartiteAccountQuery query) {
        return sysUserTripartiteAccountRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<SysUserTripartiteAccountDTO> details(Long id) {
        return sysUserTripartiteAccountRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(SysUserTripartiteAccountUpdateCommand cmd) {
        return sysUserTripartiteAccountRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(SysUserTripartiteAccountAddCommand cmd) {
        return sysUserTripartiteAccountRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<SysUserTripartiteAccountAddCommand> cmds) {
        return sysUserTripartiteAccountRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysUserTripartiteAccountRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 根据邮箱查询绑定信息
     *
     * @param email
     * @return
     */
    public Result<SysUserTripartiteAccountDTO> getByEmail(String email) {
        return sysUserTripartiteAccountRepositoryFeign.getByEmail(email);
    }

    /**
     * 根据查询条件查询绑定信息
     *
     * @param query
     * @return
     */
    public Result<SysUserTripartiteAccountDTO> getByTripartite(SysUserTripartiteAccountQuery query) {
        return sysUserTripartiteAccountRepositoryFeign.getByTripartite(query);
    }
}