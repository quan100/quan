package com.quan.app.core.system.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.app.common.constant.RedisKey;
import com.quan.app.common.util.Validate;
import com.quan.common.base.constant.CommonConstant;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import com.quan.app.core.system.entity.SysUserInfoPO;
import com.quan.app.core.system.mapper.SysUserInfoMapper;
import com.quan.app.core.system.repository.SysUserInfoRepository;
import com.quan.tools.redis.service.CacheUtil;
import org.springframework.stereotype.Repository;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
@Repository
public class SysUserInfoRepositoryImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfoPO> implements SysUserInfoRepository {

    @Override
    public PageResult<SysUserInfoPO> page(SysUserInfoPO po, BasePage basePage) {
        Page<SysUserInfoPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<SysUserInfoPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public SysUserInfoPO getUserInfo(String userId) {
        String userInfoKey = RedisKey.userInfoKey(userId);
        SysUserInfoPO userInfoPo = CacheUtil.get(userInfoKey, SysUserInfoPO.class);
        if (Validate.isNull(userInfoPo)) {
            LambdaQueryWrapper<SysUserInfoPO> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(SysUserInfoPO::getUserId, userId);
            queryWrapper.eq(SysUserInfoPO::getDelFlag, CommonConstant.FALSE);
            userInfoPo = this.getOne(queryWrapper);
            if (Validate.isNotNull(userInfoPo)) {
                CacheUtil.set(userInfoKey, userInfoPo);
            }
        }
        return userInfoPo;
    }

}
