package com.quan.app.core.system.repository;

import com.quan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.common.base.message.BasePage;
import com.quan.app.core.system.entity.SysUserInfoPO;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
public interface SysUserInfoRepository extends IService<SysUserInfoPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<SysUserInfoPO> page(SysUserInfoPO po, BasePage basePage);

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return
     */
    SysUserInfoPO getUserInfo(String userId);

}

