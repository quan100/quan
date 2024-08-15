package cn.javaquan.app.core.system.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.app.common.module.system.PermissionRoleDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.system.entity.SysRolePermissionPO;

import java.util.List;

/**
 * 角色权限配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface SysRolePermissionRepository extends IService<SysRolePermissionPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<SysRolePermissionPO> page(SysRolePermissionPO po, BasePage basePage);

    /**
     * 获取角色配置的权限ID.
     * @param query 查询参数
     * @return 查询参数
     */
    List<Long> getRolePermissionIds(SysRolePermissionQuery query);

    /**
     * 根据角色ID查询数量.
     * @param query 查询参数
     * @return 查询参数
     */
    int count(SysRolePermissionQuery query);

    /**
     * 根据角色ID删除资源ID列表.
     * @param event 删除要求的参数
     * @return 删除结果
     */
    boolean delRolePermission(SysRolePermissionEvent event);

    /**
     * 角色权限.
     * @return 角色权限
     */
    List<PermissionRoleDTO> getPermissionRoles();

}
