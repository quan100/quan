package cn.javaquan.app.core.system.repository;

import cn.javaquan.app.common.module.system.SysUserAddCommand;
import cn.javaquan.app.common.module.system.SysUserUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserVO;

import java.util.List;

/**
 * 用户信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface SysUserRepository {

    /**
     * 根据主键获取用户信息.
     * @param id 用户信息主键
     * @return 用户信息
     */
    SysUserVO details(Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    boolean update(SysUserUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    boolean save(SysUserAddCommand cmd);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    boolean deleteByIds(List<Long> ids);

}
