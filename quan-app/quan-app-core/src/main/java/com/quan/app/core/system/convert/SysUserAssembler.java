package com.quan.app.core.system.convert;

import com.quan.app.common.module.system.SysUserAccountVO;
import com.quan.app.common.module.system.SysUserInfoVO;
import com.quan.app.common.module.system.SysUserTripartiteAccountVO;
import com.quan.app.common.module.system.SysUserVO;
import com.quan.app.core.system.entity.SysUserAccountPO;
import com.quan.app.core.system.entity.SysUserInfoPO;
import com.quan.app.core.system.entity.SysUserTripartiteAccountPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wangquan
 */
@Mapper
public interface SysUserAssembler {

    SysUserAssembler INSTANCE = Mappers.getMapper(SysUserAssembler.class);

    default SysUserVO toSysUserVO(SysUserInfoPO info, SysUserAccountPO account, SysUserTripartiteAccountPO tripartiteAccount) {
        SysUserVO vo = new SysUserVO();
        vo.setInfo(toSysUserInfoVO(info));
        vo.setAccount(toSysUserAccountVO(account));
        vo.setTripartiteAccount(toSysUserTripartiteAccountVO(tripartiteAccount));
        return vo;
    }

    SysUserInfoVO toSysUserInfoVO(SysUserInfoPO dto);

    SysUserAccountVO toSysUserAccountVO(SysUserAccountPO dto);

    SysUserTripartiteAccountVO toSysUserTripartiteAccountVO(SysUserTripartiteAccountPO dto);
}
