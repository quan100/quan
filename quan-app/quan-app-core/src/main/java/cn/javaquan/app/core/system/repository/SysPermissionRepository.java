package cn.javaquan.app.core.system.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.app.common.module.system.SubsetPermissionsQuery;
import cn.javaquan.app.common.module.system.SysPermissionTreeDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.system.entity.SysPermissionPO;

import java.util.List;

/**
 * 系统资源权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface SysPermissionRepository extends IService<SysPermissionPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<SysPermissionPO> page(SysPermissionPO po, BasePage basePage);

    /**
     * 获取角色有效权限列表.
     * @param query 查询参数，type类型，0：一级菜单，1：菜单，2：按钮；默认查询全部
     * @return 查询结果
     */
    List<SysPermissionPO> getRolePermission(SysRolePermissionQuery query);

    /**
     * 子权限查询.
     * @param query 查询参数
     * @return 查询参数
     */
    PageResult<SysPermissionPO> getSubsetPermissions(SubsetPermissionsQuery query);

    /**
     * 子权限查询.
     * @param query 查询参数
     * @return 查询参数
     */
    PageResult<SysPermissionTreeDTO> getTreePermissions(SubsetPermissionsQuery query);

    /**
     * 获取权限列表，提供网关权限配置.
     * @return 查询结果
     */
    List<SysPermissionPO> getPerms();

}
