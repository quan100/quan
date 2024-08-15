package cn.javaquan.app.core.system.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.common.base.constant.CommonConstant;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.constant.RedisKey;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import cn.javaquan.app.common.module.system.convert.SystemAssembler;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.core.system.convert.SysUserTripartiteAccountAssembler;
import cn.javaquan.app.core.system.entity.SysUserAccountPO;
import cn.javaquan.app.core.system.entity.SysUserTripartiteAccountPO;
import cn.javaquan.app.core.system.mapper.SysUserTripartiteAccountMapper;
import cn.javaquan.app.core.system.repository.SysUserAccountRepository;
import cn.javaquan.app.core.system.repository.SysUserTripartiteAccountRepository;
import cn.javaquan.tools.redis.service.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户第三方账户.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Repository
public class SysUserTripartiteAccountRepositoryImpl
        extends ServiceImpl<SysUserTripartiteAccountMapper, SysUserTripartiteAccountPO>
        implements SysUserTripartiteAccountRepository {

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
     * 根据账号查询信息.
     * @param userIds 用户id列表
     * @return 查询结果
     */
    public List<SysUserTripartiteAccountPO> getByUserIds(List<String> userIds) {
        LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysUserTripartiteAccountPO::getUserId, userIds);
        queryWrapper.eq(SysUserTripartiteAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.list(queryWrapper);
    }

    /**
     * 根据账号查询信息.
     * @param userId 用户id
     * @return 查询结果
     */
    public SysUserTripartiteAccountPO getByUserId(String userId) {
        LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserTripartiteAccountPO::getUserId, userId);
        queryWrapper.eq(SysUserTripartiteAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.getOne(queryWrapper);
    }

    /**
     * 验证用户是否存在.
     * @param userId 用户id
     */
    private void verifyUserAccount(String userId) {
        if (Validate.isBlank(userId)) {
            return;
        }
        SysUserAccountPO sysUserInfo = sysUserAccountRepository
            .getUserAccount(SystemAssembler.INSTANCE.toSysUserAccountQuery(null, userId));
        Validate.isNotNull(sysUserInfo, ErrorCodeEnum.PM_USER_INFO_NOT_EXIST_ERR);
    }

    /**
     * 验证账号是否存在.
     * @param thirdId 第三方账号id
     * @param newThirdId 新的第三方账号id
     * @param thirdType 第三方账号类型
     * @param newThirdType 新的第三方账号类型
     */
    private void verifyAccountExists(String thirdId, String newThirdId, String thirdType, String newThirdType) {
        if (thirdId.equals(newThirdId) && thirdType.equals(newThirdType)) {
            return;
        }
        verifyAccountExists(newThirdId, newThirdType);
    }

    /**
     * 验证账号是否存在.
     * @param thirdId 第三方账号id
     * @param thirdType 第三方账号类型
     */
    private void verifyAccountExists(String thirdId, String thirdType) {
        Validate.isFalse(countByAccount(thirdId, thirdType) > 0, ErrorCodeEnum.PM_THIRD_ID_EXIST_ERR);
    }

    /**
     * 根据账号查询数量.
     * @param thirdId 第三方账号id
     * @param thirdType 第三方账号类型
     * @return 第三方账号数量
     */
    public int countByAccount(String thirdId, String thirdType) {
        LambdaQueryWrapper<SysUserTripartiteAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserTripartiteAccountPO::getThirdId, thirdId);
        queryWrapper.eq(SysUserTripartiteAccountPO::getThirdType, thirdType);
        queryWrapper.eq(SysUserTripartiteAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.count(queryWrapper);
    }

}
