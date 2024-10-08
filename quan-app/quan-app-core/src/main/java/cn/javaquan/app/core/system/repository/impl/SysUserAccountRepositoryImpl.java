package cn.javaquan.app.core.system.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountDTO;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.constant.CommonConstant;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.system.convert.SysUserAccountAssembler;
import cn.javaquan.app.core.system.entity.SysUserAccountPO;
import cn.javaquan.app.core.system.entity.SysUserRolePO;
import cn.javaquan.app.core.system.mapper.SysUserAccountMapper;
import cn.javaquan.app.core.system.repository.SysUserAccountRepository;
import cn.javaquan.app.core.system.repository.SysUserRoleRepository;
import cn.javaquan.tools.crypto.CryptoParam;
import cn.javaquan.tools.crypto.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户账号.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Repository
public class SysUserAccountRepositoryImpl extends ServiceImpl<SysUserAccountMapper, SysUserAccountPO>
        implements SysUserAccountRepository {

    private final PasswordService passwordService;

    private final SysUserRoleRepository sysUserRoleRepository;

    @Override
    public PageResult<SysUserAccountPO> page(SysUserAccountQuery query, BasePage basePage) {
        SysUserAccountPO po = SysUserAccountAssembler.INSTANCE.toQueryPO(query);
        Page<SysUserAccountPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<SysUserAccountPO> queryWrapper = Wrappers.lambdaQuery(po);
        queryWrapper.like(Validate.isNotBlank(query.getAccount()), SysUserAccountPO::getAccount, query.getAccount());
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    /**
     * 根据账号查询信息.
     * @param query 查询参数
     * @return 查询参数
     */
    @Override
    public SysUserAccountPO getUserAccount(SysUserAccountQuery query) {
        LambdaQueryWrapper<SysUserAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Validate.isNotBlank(query.getAccount()), SysUserAccountPO::getAccount, query.getAccount());
        queryWrapper.eq(Validate.isNotBlank(query.getUserId()), SysUserAccountPO::getUserId, query.getUserId());
        queryWrapper.eq(SysUserAccountPO::getDelFlag, CommonConstant.FALSE);
        SysUserAccountPO userAccountPo = this.getOne(queryWrapper);
        return userAccountPo;
    }

    @Override
    public SysUserAccountDTO getUserAccountById(Long id) {
        SysUserAccountPO sysUserAccountPO = this.getById(id);
        SysUserAccountDTO sysUserAccountDTO = SysUserAccountAssembler.INSTANCE.toSysUserAccountDTO(sysUserAccountPO);
        if (null != sysUserAccountDTO) {
            List<SysUserRolePO> sysUserRolePOS = sysUserRoleRepository.getUserRole(sysUserAccountDTO.getUserId());
            if (Validate.isNotEmpty(sysUserRolePOS)) {
                sysUserAccountDTO
                    .setRoleIdList(sysUserRolePOS.stream().map(SysUserRolePO::getRoleId).collect(Collectors.toList()));
            }
        }
        return sysUserAccountDTO;
    }

    @Override
    public boolean removeByUserIds(List<String> userIds) {
        List<SysUserAccountPO> userAccountPOS = getBytUserIds(userIds);
        if (Validate.isEmpty(userAccountPOS)) {
            return true;
        }
        List<Long> ids = userAccountPOS.stream().map(SysUserAccountPO::getId).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserAccount(SysUserAccountUpdateCommand cmd) {
        SysUserAccountPO account = SysUserAccountAssembler.INSTANCE.toUpdatePO(cmd);

        SysUserAccountPO oldAccount = this.getById(cmd.getId());
        verifyAccountExists(oldAccount.getAccount(), cmd.getAccount());

        if (Validate.isNotBlank(cmd.getPassword())) {
            CryptoParam cryptoParam = passwordService.encryptPassword(cmd.getAccount(), cmd.getPassword());

            account.setSalt(cryptoParam.getSalt());
            account.setSecret(cryptoParam.getSecret());
        }

        Validate.isTrue(this.saveOrUpdate(account), "账号信息更新失败");

        // 添加角色
        sysUserRoleRepository.saveUserRole(cmd.getRoleIdList(), oldAccount.getUserId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserAccount(SysUserAccountAddCommand cmd) {
        SysUserAccountPO account = SysUserAccountAssembler.INSTANCE.toAddPO(cmd);
        Validate.isNotNull(account, ErrorCodeEnum.PARAM_ERROR);
        Validate.isNotBlank(cmd.getUserId(), ErrorCodeEnum.PARAM_ERROR);

        verifyAccountExists(cmd.getAccount());

        CryptoParam cryptoParam = passwordService.encryptPassword(cmd.getAccount(), cmd.getPassword());

        account.setSalt(cryptoParam.getSalt());
        account.setSecret(cryptoParam.getSecret());

        Validate.isTrue(this.save(account), "账号信息添加失败");
        // 添加角色
        sysUserRoleRepository.saveUserRole(cmd.getRoleIdList(), account.getUserId());
    }

    /**
     * 根据账号查询信息.
     * @param userIds 用户id集合
     * @return 用户账号信息
     */
    public List<SysUserAccountPO> getBytUserIds(List<String> userIds) {
        LambdaQueryWrapper<SysUserAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysUserAccountPO::getUserId, userIds);
        queryWrapper.eq(SysUserAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.list(queryWrapper);
    }

    /**
     * 验证账号是否存在.
     * @param account 原账号
     * @param newAccount 新账号
     */
    private void verifyAccountExists(String account, String newAccount) {
        if (Validate.isBlank(newAccount) || account.equals(newAccount)) {
            return;
        }
        verifyAccountExists(newAccount);
    }

    /**
     * 验证账号是否存在.
     * @param account 账号
     */
    private void verifyAccountExists(String account) {
        Validate.isFalse(countByAccount(account) > 0, "账号已存在！");
    }

    /**
     * 根据账号查询数量.
     * @param account 用户账号
     * @return 账号数量
     */
    public int countByAccount(String account) {
        LambdaQueryWrapper<SysUserAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserAccountPO::getAccount, account);
        queryWrapper.eq(SysUserAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.count(queryWrapper);
    }

    /**
     * 根据用户ID查询.
     * @param userId 用户id
     * @return 用户账号信息
     */
    public SysUserAccountPO getByUserId(String userId) {
        LambdaQueryWrapper<SysUserAccountPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserAccountPO::getUserId, userId);
        queryWrapper.eq(SysUserAccountPO::getDelFlag, CommonConstant.FALSE);
        return this.getOne(queryWrapper);
    }

}
