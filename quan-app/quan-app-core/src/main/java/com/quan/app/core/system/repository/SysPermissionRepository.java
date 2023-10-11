package com.quan.app.core.system.repository;

import com.quan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.app.common.module.system.SubsetPermissionsQuery;
import com.quan.app.common.module.system.SysPermissionTreeDTO;
import com.quan.app.common.module.system.SysRolePermissionQuery;
import com.quan.common.base.message.BasePage;
import com.quan.app.core.system.entity.SysPermissionPO;

import java.util.List;

/**
 * 系统资源权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
public interface SysPermissionRepository extends IService<SysPermissionPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<SysPermissionPO> page(SysPermissionPO po, BasePage basePage);

    /**
     * 获取角色有效权限列表
     *
     * @param query type类型，0：一级菜单，1：菜单，2：按钮；默认查询全部
     * @return
     */
    List<SysPermissionPO> getRolePermission(SysRolePermissionQuery query);

    /**
     * 子权限查询
     *
     * @param query
     * @return
     */
    PageResult<SysPermissionPO> getSubsetPermissions(SubsetPermissionsQuery query);

    /**
     * 子权限查询
     *
     * @param query
     * @return
     */
    PageResult<SysPermissionTreeDTO> getTreePermissions(SubsetPermissionsQuery query);

    /**
     * 获取权限列表，提供网关权限配置
     *
     * @return
     */
    List<SysPermissionPO> getPerms();

}

