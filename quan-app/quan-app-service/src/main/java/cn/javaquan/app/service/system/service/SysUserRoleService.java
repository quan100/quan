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
 * 用户角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@Component
public class SysUserRoleService {

    private final SysUserRoleRepositoryFeign sysUserRoleRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<SysUserRoleDTO>> page(SysUserRoleQuery query) {
        return sysUserRoleRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<SysUserRoleDTO> details(Long id) {
        return sysUserRoleRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(SysUserRoleUpdateCommand cmd) {
        return sysUserRoleRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(SysUserRoleAddCommand cmd) {
        return sysUserRoleRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<SysUserRoleAddCommand> cmds) {
        return sysUserRoleRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysUserRoleRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    public Result<List<SysUserRoleDTO>> getUserRole(String userId) {
        return sysUserRoleRepositoryFeign.getUserRole(userId);
    }

    /**
     * 根据角色ID查询数量
     *
     * @param roleIds
     * @return
     */
    public Result<Integer> getCount(List<Long> roleIds) {
        return sysUserRoleRepositoryFeign.getCount(roleIds);
    }

    /**
     * 删除
     *
     * @param roleIds
     * @return
     */
    public Result<Boolean> delByRoleId(List<Long> roleIds) {
        return sysUserRoleRepositoryFeign.delByRoleId(roleIds);
    }
}
