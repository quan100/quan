package cn.javaquan.app.core.system.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.system.entity.SysRolePO;

import java.util.List;

/**
 * 角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
public interface SysRoleRepository extends IService<SysRolePO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<SysRolePO> page(SysRolePO po, BasePage basePage);

    /**
     * 根据角色编码查询角色
     *
     * @param query
     * @return
     */
    SysRolePO getRole(SysRoleQuery query);

    /**
     * 获取角色数据
     *
     * @param query
     * @return
     */
    List<SysRolePO> getRoles(SysRoleQuery query);

    /**
     * 删除角色，同时删除角色配置的权限
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(List<Long> ids);

}

