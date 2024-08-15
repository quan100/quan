package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountDTO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.feign.SysUserTripartiteAccountRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户第三方账户.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class SysUserTripartiteAccountService {

    private final SysUserTripartiteAccountRepositoryFeign sysUserTripartiteAccountRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<SysUserTripartiteAccountDTO>> page(SysUserTripartiteAccountQuery query) {
        return sysUserTripartiteAccountRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<SysUserTripartiteAccountDTO> details(Long id) {
        return sysUserTripartiteAccountRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(SysUserTripartiteAccountUpdateCommand cmd) {
        return sysUserTripartiteAccountRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(SysUserTripartiteAccountAddCommand cmd) {
        return sysUserTripartiteAccountRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<SysUserTripartiteAccountAddCommand> cmds) {
        return sysUserTripartiteAccountRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysUserTripartiteAccountRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 根据邮箱查询绑定信息.
     * @param email 邮箱
     * @return 第三方账号绑定信息
     */
    public Result<SysUserTripartiteAccountDTO> getByEmail(String email) {
        return sysUserTripartiteAccountRepositoryFeign.getByEmail(email);
    }

    /**
     * 根据查询条件查询绑定信息.
     * @param query 查询参数
     * @return 第三方账号绑定信息
     */
    public Result<SysUserTripartiteAccountDTO> getByTripartite(SysUserTripartiteAccountQuery query) {
        return sysUserTripartiteAccountRepositoryFeign.getByTripartite(query);
    }

}
