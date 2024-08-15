package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountDTO;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.app.service.system.feign.SysUserAccountRepositoryFeign;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户账号.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class SysUserAccountService {

    private final SysUserAccountRepositoryFeign sysUserAccountRepositoryFeign;

    private final PermissionService permissionService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<SysUserAccountDTO>> page(SysUserAccountQuery query) {
        return sysUserAccountRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<SysUserAccountDTO> details(Long id) {
        return sysUserAccountRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(SysUserAccountUpdateCommand cmd) {
        return sysUserAccountRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(SysUserAccountAddCommand cmd) {
        return sysUserAccountRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<SysUserAccountAddCommand> cmds) {
        return sysUserAccountRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysUserAccountRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取用户权限.
     * @param query 查询参数
     * @return 用户权限
     */
    public Result<List<UserPermissionTreeDTO>> getUserPermission(AuthQuery query) {
        return Result.success(permissionService.getUserPermission(query));
    }

    /**
     * 查询账号信息.
     * @param query 查询参数
     * @return 用户账号信息
     */
    public Result<SysUserAccountDTO> getUserAccount(SysUserAccountQuery query) {
        return sysUserAccountRepositoryFeign.getUserAccount(query);
    }

}
