package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.module.system.SysUserRoleAddCommand;
import cn.javaquan.app.common.module.system.SysUserRoleDTO;
import cn.javaquan.app.common.module.system.SysUserRoleQuery;
import cn.javaquan.app.common.module.system.SysUserRoleUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.feign.SysUserRoleRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class SysUserRoleService {

    private final SysUserRoleRepositoryFeign sysUserRoleRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<SysUserRoleDTO>> page(SysUserRoleQuery query) {
        return sysUserRoleRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<SysUserRoleDTO> details(Long id) {
        return sysUserRoleRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(SysUserRoleUpdateCommand cmd) {
        return sysUserRoleRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(SysUserRoleAddCommand cmd) {
        return sysUserRoleRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<SysUserRoleAddCommand> cmds) {
        return sysUserRoleRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysUserRoleRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 根据用户ID查询.
     * @param userId 用户id
     * @return 用户角色
     */
    public Result<List<SysUserRoleDTO>> getUserRole(String userId) {
        return sysUserRoleRepositoryFeign.getUserRole(userId);
    }

    /**
     * 根据角色ID查询数量.
     * @param roleIds 角色id
     * @return 角色数量
     */
    public Result<Integer> getCount(List<Long> roleIds) {
        return sysUserRoleRepositoryFeign.getCount(roleIds);
    }

    /**
     * 删除.
     * @param roleIds 角色id
     * @return 操作是否成功
     */
    public Result<Boolean> delByRoleId(List<Long> roleIds) {
        return sysUserRoleRepositoryFeign.delByRoleId(roleIds);
    }

}
