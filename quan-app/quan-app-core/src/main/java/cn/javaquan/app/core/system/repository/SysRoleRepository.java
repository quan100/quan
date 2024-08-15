package cn.javaquan.app.core.system.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.system.entity.SysRolePO;

import java.util.List;

/**
 * 角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface SysRoleRepository extends IService<SysRolePO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<SysRolePO> page(SysRolePO po, BasePage basePage);

    /**
     * 根据角色编码查询角色.
     * @param query 查询参数
     * @return 查询参数
     */
    SysRolePO getRole(SysRoleQuery query);

    /**
     * 获取角色数据.
     * @param query 查询参数
     * @return 查询参数
     */
    List<SysRolePO> getRoles(SysRoleQuery query);

    /**
     * 删除角色，同时删除角色配置的权限.
     * @param ids 角色id
     * @return 删除结果
     */
    boolean deleteByIds(List<Long> ids);

}
