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
 * 用户账号
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
public interface SysUserAccountRepository extends IService<SysUserAccountPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param query
     * @param basePage
     * @return
     */
    PageResult<SysUserAccountPO> page(SysUserAccountQuery query, BasePage basePage);

    /**
     * 查询用户账号信息
     *
     * @param query
     * @return
     */
    SysUserAccountPO getUserAccount(SysUserAccountQuery query);

    /**
     * 查询用户账号信息
     *
     * @param id
     * @return
     */
    SysUserAccountDTO getUserAccountById(Long id);

    boolean removeByUserIds(List<String> userIds);

    /**
     * 更新用户账号
     *
     * @param cmd
     * @return
     */
    void updateUserAccount(SysUserAccountUpdateCommand cmd);

    /**
     * 新增用户账号
     *
     * @param cmd
     * @return
     */
    void addUserAccount(SysUserAccountAddCommand cmd);

}

