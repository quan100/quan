package cn.javaquan.app.core.system.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import cn.javaquan.app.core.system.entity.SysUserTripartiteAccountPO;

import java.util.List;

/**
 * 用户第三方账户.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface SysUserTripartiteAccountRepository extends IService<SysUserTripartiteAccountPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<SysUserTripartiteAccountPO> page(SysUserTripartiteAccountPO po, BasePage basePage);

    /**
     * 根据邮箱查询绑定信息.
     * @param email 邮箱
     * @return 查询结果
     */
    SysUserTripartiteAccountPO getByEmail(String email);

    /**
     * 通过三方信息获取绑定信息.
     * @param query 查询参数
     * @return 查询参数
     */
    SysUserTripartiteAccountPO getByTripartite(SysUserTripartiteAccountQuery query);

    /**
     * 根据用户ID批量删除.
     * @param userIds 用户ID
     * @return 操作是否成功
     */
    boolean removeByUserIds(List<String> userIds);

    /**
     * 更新用户账号.
     * @param cmd 更新指令参数
     */
    void updateTripartiteAccount(SysUserTripartiteAccountUpdateCommand cmd);

    /**
     * 新增用户账号.
     * @param cmd 更新指令参数
     */
    void addTripartiteAccount(SysUserTripartiteAccountAddCommand cmd);

    /**
     * 获取用户绑定的第三方账号.
     * @param userId 用户ID
     * @param thirdType 第三方账号类型
     * @return 查询结果
     */
    SysUserTripartiteAccountPO getTripartiteUserAccount(String userId, String thirdType);

}
