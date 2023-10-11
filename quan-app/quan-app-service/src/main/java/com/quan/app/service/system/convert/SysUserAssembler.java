package com.quan.app.service.system.convert;

import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wangquan
 */
@Mapper
public interface SysUserAssembler {

    SysUserAssembler INSTANCE = Mappers.getMapper(SysUserAssembler.class);

    default SysUserVO toSysUserVO(SysUserInfoDTO info, SysUserAccountDTO account, SysUserTripartiteAccountDTO tripartiteAccount) {
        SysUserVO vo = new SysUserVO();
        vo.setInfo(toSysUserInfoVO(info));
        vo.setAccount(toSysUserAccountVO(account));
        vo.setTripartiteAccount(toSysUserTripartiteAccountVO(tripartiteAccount));
        return vo;
    }

    SysUserInfoVO toSysUserInfoVO(SysUserInfoDTO dto);

    SysUserAccountVO toSysUserAccountVO(SysUserAccountDTO dto);

    SysUserTripartiteAccountVO toSysUserTripartiteAccountVO(SysUserTripartiteAccountDTO dto);
}
