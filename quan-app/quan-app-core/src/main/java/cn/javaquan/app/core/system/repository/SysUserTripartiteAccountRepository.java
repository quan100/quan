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
 * 用户第三方账户
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
public interface SysUserTripartiteAccountRepository extends IService<SysUserTripartiteAccountPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<SysUserTripartiteAccountPO> page(SysUserTripartiteAccountPO po, BasePage basePage);

    /**
     * 根据邮箱查询绑定信息
     *
     * @param email 邮箱
     * @return
     */
    SysUserTripartiteAccountPO getByEmail(String email);

    /**
     * 通过三方信息获取绑定信息
     *
     * @param query
     * @return
     */
    SysUserTripartiteAccountPO getByTripartite(SysUserTripartiteAccountQuery query);

    boolean removeByUserIds(List<String> userIds);

    /**
     * 更新用户账号
     *
     * @param cmd
     * @return
     */
    void updateTripartiteAccount(SysUserTripartiteAccountUpdateCommand cmd);

    /**
     * 新增用户账号
     *
     * @param cmd
     * @return
     */
    void addTripartiteAccount(SysUserTripartiteAccountAddCommand cmd);

    /**
     * @param userId    用户ID
     * @param thirdType
     * @return
     */
    SysUserTripartiteAccountPO getTripartiteUserAccount(String userId, String thirdType);

}

