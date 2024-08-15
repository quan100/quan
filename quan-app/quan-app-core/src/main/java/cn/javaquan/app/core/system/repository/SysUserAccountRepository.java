package cn.javaquan.app.core.system.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountDTO;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.system.entity.SysUserAccountPO;

import java.util.List;

/**
 * 用户账号.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface SysUserAccountRepository extends IService<SysUserAccountPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param query 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<SysUserAccountPO> page(SysUserAccountQuery query, BasePage basePage);

    /**
     * 查询用户账号信息.
     * @param query 查询参数
     * @return 查询参数
     */
    SysUserAccountPO getUserAccount(SysUserAccountQuery query);

    /**
     * 查询用户账号信息.
     * @param id 用户账号信息主键
     * @return 账号信息
     */
    SysUserAccountDTO getUserAccountById(Long id);

    /**
     * 根据用户ID删除用户账号.
     * @param userIds 用户ID
     * @return 操作是否成功
     */
    boolean removeByUserIds(List<String> userIds);

    /**
     * 更新用户账号.
     * @param cmd 更新指令参数
     */
    void updateUserAccount(SysUserAccountUpdateCommand cmd);

    /**
     * 新增用户账号.
     * @param cmd 更新指令参数
     */
    void addUserAccount(SysUserAccountAddCommand cmd);

}
