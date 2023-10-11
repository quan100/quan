package com.quan.app.core.system.repository;

import com.quan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.common.base.message.BasePage;
import com.quan.app.core.system.entity.SysUserRolePO;

import java.util.List;

/**
 * 用户角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
public interface SysUserRoleRepository extends IService<SysUserRolePO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<SysUserRolePO> page(SysUserRolePO po, BasePage basePage);

    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    List<SysUserRolePO> getUserRole(String userId);

    /**
     * 根据角色ID查询数量
     *
     * @param roleIds
     * @return
     */
    int getCount(List<Long> roleIds);

    /**
     * 根据角色ID删除资源ID列表
     *
     * @param roleIds
     * @return
     */
    boolean delByRoleId(List<Long> roleIds);

    /**
     * 保存用户角色
     *
     * @param roleIds
     * @param userId
     */
    void saveUserRole(List<Long> roleIds, String userId);

}

