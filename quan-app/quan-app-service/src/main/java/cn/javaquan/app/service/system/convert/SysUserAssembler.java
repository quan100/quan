package cn.javaquan.app.service.system.convert;

import cn.javaquan.app.common.module.system.SysUserAccountDTO;
import cn.javaquan.app.common.module.system.SysUserAccountVO;
import cn.javaquan.app.common.module.system.SysUserInfoDTO;
import cn.javaquan.app.common.module.system.SysUserInfoVO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountDTO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountVO;
import cn.javaquan.app.common.module.system.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户信息转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper
public interface SysUserAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    SysUserAssembler INSTANCE = Mappers.getMapper(SysUserAssembler.class);

    /**
     * 转换为用户信息VO.
     * @param info 用户信息
     * @param account 账号信息
     * @param tripartiteAccount 三方账号信息
     * @return 用户信息VO
     */
    default SysUserVO toSysUserVO(SysUserInfoDTO info, SysUserAccountDTO account,
            SysUserTripartiteAccountDTO tripartiteAccount) {
        SysUserVO vo = new SysUserVO();
        vo.setInfo(toSysUserInfoVO(info));
        vo.setAccount(toSysUserAccountVO(account));
        vo.setTripartiteAccount(toSysUserTripartiteAccountVO(tripartiteAccount));
        return vo;
    }

    /**
     * 转换为用户信息VO.
     * @param dto 用户信息
     * @return 用户信息VO
     */
    SysUserInfoVO toSysUserInfoVO(SysUserInfoDTO dto);

    /**
     * 转换为用户账号信息VO.
     * @param dto 用户账号信息
     * @return 用户账号信息VO
     */
    SysUserAccountVO toSysUserAccountVO(SysUserAccountDTO dto);

    /**
     * 转换为用户三方账号信息VO.
     * @param dto 用户三方账号信息
     * @return 用户三方账号信息VO
     */
    SysUserTripartiteAccountVO toSysUserTripartiteAccountVO(SysUserTripartiteAccountDTO dto);

}
