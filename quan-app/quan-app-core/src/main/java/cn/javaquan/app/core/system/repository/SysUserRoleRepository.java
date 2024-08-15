package cn.javaquan.app.core.system.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.system.entity.SysUserRolePO;

import java.util.List;

/**
 * 用户角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface SysUserRoleRepository extends IService<SysUserRolePO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<SysUserRolePO> page(SysUserRolePO po, BasePage basePage);

    /**
     * 根据用户ID查询.
     * @param userId 用户ID
     * @return 用户关联的角色
     */
    List<SysUserRolePO> getUserRole(String userId);

    /**
     * 根据角色ID查询数量.
     * @param roleIds 角色ID列表
     * @return 数量
     */
    int getCount(List<Long> roleIds);

    /**
     * 根据角色ID删除资源ID列表.
     * @param roleIds 角色ID列表
     * @return 删除结果
     */
    boolean delByRoleId(List<Long> roleIds);

    /**
     * 保存用户角色.
     * @param roleIds 角色ID列表
     * @param userId 用户ID
     */
    void saveUserRole(List<Long> roleIds, String userId);

}
