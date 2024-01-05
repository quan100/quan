package cn.javaquan.app.core.system.repository.impl;

import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.module.system.SysUserAddCommand;
import cn.javaquan.app.common.module.system.SysUserUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserVO;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.core.system.convert.SysUserAssembler;
import cn.javaquan.app.core.system.convert.SysUserInfoAssembler;
import cn.javaquan.app.core.system.entity.SysUserAccountPO;
import cn.javaquan.app.core.system.entity.SysUserInfoPO;
import cn.javaquan.app.core.system.entity.SysUserTripartiteAccountPO;
import cn.javaquan.app.core.system.repository.SysUserAccountRepository;
import cn.javaquan.app.core.system.repository.SysUserInfoRepository;
import cn.javaquan.app.core.system.repository.SysUserRepository;
import cn.javaquan.tools.id.ID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@Component
public class SysUserRepositoryImpl implements SysUserRepository {

    private final SysUserAccountRepository sysUserAccountRepository;
    private final SysUserInfoRepository sysUserInfoRepository;

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public SysUserVO details(Long id) {
        SysUserInfoPO sysUserInfo = sysUserInfoRepository.getById(id);

        if (null == sysUserInfo) {
            return null;
        }
        SysUserAccountPO sysUserAccount = null;
        SysUserTripartiteAccountPO sysUserTripartiteAccount = null;


        String userId = sysUserInfo.getUserId();
        if (Validate.isNotBlank(userId)) {
//            SysUserAccountQuery sysUserAccountQuery = SysUserAccountQuery.userId(userId);
//            sysUserAccount = sysUserAccountRepository.getUserAccount(sysUserAccountQuery);
            // sysUserTripartiteAccount = sysUserTripartiteAccountRepository.getTripartiteUserAccount(userId);
        }

        return SysUserAssembler.INSTANCE.toSysUserVO(
                sysUserInfo, sysUserAccount, sysUserTripartiteAccount
        );
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(SysUserUpdateCommand cmd) {
        SysUserInfoPO info = SysUserInfoAssembler.INSTANCE.toUpdatePO(cmd.getInfo());
        SysUserInfoPO infoPO = sysUserInfoRepository.getById(info.getId());
        Validate.isNotNull(infoPO, ErrorCodeEnum.DATA_NOT_EXIST_ERROR);

//        RunUtil.doRun(sysUserInfoRepository.updateById(info), () -> {
//            sysUserAccountRepository.updateUserAccount(infoPO.getUserId(), cmd.getAccount());
//            sysUserTripartiteAccountRepository.updateTripartiteAccount(infoPO.getUserId(), cmd.getTripartiteAccount());
//        });
        return true;
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysUserAddCommand cmd) {
        String userId = ID.genId();
        SysUserInfoPO info = SysUserInfoAssembler.INSTANCE.toAddPO(cmd.getInfo());
        info.setUserId(userId);

        RunUtil.doRun(sysUserInfoRepository.save(info), () -> {
//            SysUserAccountAddCommand account = cmd.getAccount();
//            if (null != account) {
//                account.setUserId(userId);
//                sysUserAccountRepository.addUserAccount(account);
//            }
//
//            SysUserTripartiteAccountPO tripartiteAccount = SysUserTripartiteAccountAssembler.INSTANCE.toAddPO(cmd.getTripartiteAccount());
//            if (null != tripartiteAccount) {
//                tripartiteAccount.setUserId(userId);
//                Validate.isTrue(sysUserTripartiteAccountRepository.save(tripartiteAccount), "账号绑定信息添加失败");
//            }
        });
        return true;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(List<Long> ids) {
        List<SysUserInfoPO> userInfoPOS = sysUserInfoRepository.listByIds(ids);

        if (Validate.isEmpty(userInfoPOS)) {
            return true;
        }

        return sysUserInfoRepository.removeByIds(ids);

//        List<String> userIds = userInfoPOS.stream().map(SysUserInfoPO::getUserId).collect(Collectors.toList());
//
//        return RunUtil.doRun(sysUserInfoRepository.removeByIds(ids), () -> {
//            return RunUtil.doRun(sysUserAccountRepository.removeByUserIds(userIds), () -> {
//                return sysUserTripartiteAccountRepository.removeByUserIds(userIds);
//            });
//        });
    }
}
