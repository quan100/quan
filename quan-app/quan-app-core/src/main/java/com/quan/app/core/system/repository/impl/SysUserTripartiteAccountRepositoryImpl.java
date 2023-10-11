package com.quan.app.core.system.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.common.base.constant.CommonConstant;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.common.constant.ErrorCodeEnum;
import com.quan.app.common.constant.RedisKey;
import com.quan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import com.quan.app.common.module.system.SysUserTripartiteAccountQuery;
import com.quan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import com.quan.app.common.module.system.convert.SystemAssembler;
import com.quan.app.common.util.Validate;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import com.quan.app.core.system.convert.SysUserTripartiteAccountAssembler;
import com.quan.app.core.system.entity.SysUserAccountPO;
import com.quan.app.core.system.entity.SysUserTripartiteAccountPO;
import com.quan.app.core.system.mapper.SysUserTripartiteAccountMapper;
import com.quan.app.core.system.repository.SysUserAccountRepository;
import com.quan.app.core.system.repository.SysUserTripartiteAccountRepository;
import com.quan.tools.redis.service.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户第三方账户
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
@RequiredArgsConstructor
@Repository
public class SysUserTripartiteAccountRepositoryImpl extends ServiceImpl<SysUserTripartiteAccountMapper, SysUserTripartiteAccountPO> implements SysUserTripartiteAccountRepository {

    private final SysUserAccountRepository sysUserAccountRepository;

    @Override
    public PageResult<SysUserTripartiteAccountPO> page(SysUserTripartiteAccountPO po, BasePage basePage) {
        Page<SysUserTripartiteAccountPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public SysUserTripartiteAccountPO getByEmail(String email) {
        String emailMd5 = DigestUtils.md5Hex(email);
        String userInfoKey = RedisKey.userAccountKey(emailMd5);
        SysUserTripartiteAccountPO accountPo = CacheUtil.get(userInfoKey, SysUserTripartiteAccountPO.class);
        if (Validate.isNull(accountPo)) {
            LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(SysUserTripartiteAccountPO::getAccount, email);
            queryWrapper.eq(SysUserTripartiteAccountPO::getDelFlag, CommonConstant.FALSE);
            accountPo = this.getOne(queryWrapper);
            if (Validate.isNotNull(accountPo)) {
                CacheUtil.set(userInfoKey, accountPo);
            }
        }
        return accountPo;
    }

    @Override
    public SysUserTripartiteAccountPO getByTripartite(SysUserTripartiteAccountQuery query) {
        String thirdId = query.getThirdId();
        String thirdType = query.getThirdType();
        String userInfoKey = RedisKey.userThirdAccountKey(thirdId, thirdType);
        SysUserTripartiteAccountPO accountPo = CacheUtil.get(userInfoKey, SysUserTripartiteAccountPO.class);
        if (Validate.isNull(accountPo)) {
            LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(SysUserTripartiteAccountPO::getThirdId, thirdId);
            queryWrapper.eq(SysUserTripartiteAccountPO::getThirdType, thirdType);
            queryWrapper.eq(SysUserTripartiteAccountPO::getDelFlag, CommonConstant.FALSE);
            accountPo = this.getOne(queryWrapper);
            if (Validate.isNotNull(accountPo)) {
                CacheUtil.set(userInfoKey, accountPo);
            }
        }
        return accountPo;
    }

    @Override
    public boolean removeByUserIds(List<String> userIds) {
        List<SysUserTripartiteAccountPO> userAccountPOS = getByUserIds(userIds);
        if (Validate.isEmpty(userAccountPOS)) {
            return true;
        }
        List<Long> ids = userAccountPOS.stream().map(SysUserTripartiteAccountPO::getId).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    @Override
    public void updateTripartiteAccount(SysUserTripartiteAccountUpdateCommand cmd) {
        SysUserTripartiteAccountPO tripartiteAccount = SysUserTripartiteAccountAssembler.INSTANCE.toUpdatePO(cmd);

        verifyUserAccount(cmd.getUserId());

        SysUserTripartiteAccountPO oldAccount = this.getById(cmd.getId());
        verifyAccountExists(oldAccount.getThirdId(), cmd.getThirdId(), oldAccount.getThirdType(), cmd.getThirdType());

        Validate.isTrue(this.updateById(tripartiteAccount), ErrorCodeEnum.OPERATION_ERROR);
        CacheUtil.del(RedisKey.userThirdAccountKey(cmd.getThirdId(), cmd.getThirdType()));
    }

    @Override
    public void addTripartiteAccount(SysUserTripartiteAccountAddCommand cmd) {
        SysUserTripartiteAccountPO tripartiteAccount = SysUserTripartiteAccountAssembler.INSTANCE.toAddPO(cmd);

        verifyUserAccount(cmd.getUserId());
        verifyAccountExists(cmd.getThirdId(), cmd.getThirdType());

        Validate.isTrue(this.save(tripartiteAccount), ErrorCodeEnum.OPERATION_ERROR);
        CacheUtil.del(RedisKey.userThirdAccountKey(cmd.getThirdId(), cmd.getThirdType()));
    }


    @Override
    public SysUserTripartiteAccountPO getTripartiteUserAccount(String userId, String thirdType) {
        LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserTripartiteAccountPO::getUserId, userId);
        queryWrapper.eq(SysUserTripartiteAccountPO::getThirdType, thirdType);
        queryWrapper.eq(SysUserTripartiteAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.getOne(queryWrapper);
    }

    /**
     * 根据账号查询信息
     *
     * @param userIds
     * @return
     */
    public List<SysUserTripartiteAccountPO> getByUserIds(List<String> userIds) {
        LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysUserTripartiteAccountPO::getUserId, userIds);
        queryWrapper.eq(SysUserTripartiteAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.list(queryWrapper);
    }

    /**
     * 根据账号查询信息
     *
     * @param userId
     * @return
     */
    public SysUserTripartiteAccountPO getByUserId(String userId) {
        LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserTripartiteAccountPO::getUserId, userId);
        queryWrapper.eq(SysUserTripartiteAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.getOne(queryWrapper);
    }

    private void verifyUserAccount(String userId) {
        if (Validate.isNotBlank(userId)) {
            SysUserAccountPO sysUserInfoPO = sysUserAccountRepository.getUserAccount(SystemAssembler.INSTANCE.toSysUserAccountQuery(null, userId));
            Validate.isNotNull(sysUserInfoPO, ErrorCodeEnum.PM_USER_INFO_NOT_EXIST_ERR);
        }
    }

    /**
     * 验证账号是否存在
     *
     * @param thirdId
     * @param newThirdId
     * @param thirdType
     */
    private void verifyAccountExists(String thirdId, String newThirdId, String thirdType, String newThirdType) {
        if (thirdId.equals(newThirdId) && thirdType.equals(newThirdType)) {
            return;
        }
        verifyAccountExists(newThirdId, newThirdType);
    }

    /**
     * 验证账号是否存在
     *
     * @param thirdId
     * @param thirdType
     */
    private void verifyAccountExists(String thirdId, String thirdType) {
        Validate.isFalse(countByAccount(thirdId, thirdType) > 0, ErrorCodeEnum.PM_THIRD_ID_EXIST_ERR);
    }

    /**
     * 根据账号查询数量
     *
     * @param thirdId
     * @param thirdType
     * @return
     */
    public int countByAccount(String thirdId, String thirdType) {
        LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserTripartiteAccountPO::getThirdId, thirdId);
        queryWrapper.eq(SysUserTripartiteAccountPO::getThirdType, thirdType);
        queryWrapper.eq(SysUserTripartiteAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.count(queryWrapper);
    }
}
