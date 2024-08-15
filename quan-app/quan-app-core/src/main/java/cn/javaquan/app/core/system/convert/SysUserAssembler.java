package cn.javaquan.app.core.system.convert;

import cn.javaquan.app.common.module.system.SysUserAccountVO;
import cn.javaquan.app.common.module.system.SysUserInfoVO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountVO;
import cn.javaquan.app.common.module.system.SysUserVO;
import cn.javaquan.app.core.system.entity.SysUserAccountPO;
import cn.javaquan.app.core.system.entity.SysUserInfoPO;
import cn.javaquan.app.core.system.entity.SysUserTripartiteAccountPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户数据转换器.
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
     * 转换为用户VO对象.
     * @param info 用户信息
     * @param account 用户账号信息
     * @param tripartiteAccount 用户第三方账号信息
     * @return 用户VO对象
     */
    default SysUserVO toSysUserVO(SysUserInfoPO info, SysUserAccountPO account,
            SysUserTripartiteAccountPO tripartiteAccount) {
        SysUserVO vo = new SysUserVO();
        vo.setInfo(toSysUserInfoVO(info));
        vo.setAccount(toSysUserAccountVO(account));
        vo.setTripartiteAccount(toSysUserTripartiteAccountVO(tripartiteAccount));
        return vo;
    }

    /**
     * 转换为用户信息VO对象.
     * @param po po
     * @return vo
     */
    SysUserInfoVO toSysUserInfoVO(SysUserInfoPO po);

    /**
     * 转换为用户账号VO对象.
     * @param po po
     * @return vo
     */
    SysUserAccountVO toSysUserAccountVO(SysUserAccountPO po);

    /**
     * 转换为用户第三方账号VO对象.
     * @param po po
     * @return vo
     */
    SysUserTripartiteAccountVO toSysUserTripartiteAccountVO(SysUserTripartiteAccountPO po);

}
