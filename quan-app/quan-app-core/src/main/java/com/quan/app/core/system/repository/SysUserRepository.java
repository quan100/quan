package com.quan.app.core.system.repository;

import com.quan.app.common.module.system.SysUserAddCommand;
import com.quan.app.common.module.system.SysUserUpdateCommand;
import com.quan.app.common.module.system.SysUserVO;

import java.util.List;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
public interface SysUserRepository {

    SysUserVO details(Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    boolean update(SysUserUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    boolean save(SysUserAddCommand cmd);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(List<Long> ids);
}
