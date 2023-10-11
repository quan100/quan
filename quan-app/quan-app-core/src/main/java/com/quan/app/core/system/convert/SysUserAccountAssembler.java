package com.quan.app.core.system.convert;

import com.quan.app.common.module.auth.TripartiteBoundEvent;
import com.quan.app.common.module.system.SysUserAccountAddCommand;
import com.quan.app.common.module.system.SysUserAccountDTO;
import com.quan.app.common.module.system.SysUserAccountQuery;
import com.quan.app.common.module.system.SysUserAccountUpdateCommand;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.common.base.constant.AppTypeEnum;
import com.quan.app.core.system.entity.SysUserAccountPO;
import com.quan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户账号参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Mapper(imports = {
        ID.class,
        LocalDateUtils.class,
        AppTypeEnum.class
})
public interface SysUserAccountAssembler {

    SysUserAccountAssembler INSTANCE = Mappers.getMapper(SysUserAccountAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    @Mapping(target = "account", ignore = true)
    SysUserAccountPO toQueryPO(SysUserAccountQuery query);

    /**
     * 转换为更新参数
     * <p>
     * 更新自动配置更新时间。
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", ignore = true)
    @Mapping(target = "userId", ignore = true)
    SysUserAccountPO toUpdatePO(SysUserAccountUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", constant = "false")
    @Mapping(target = "userId", expression = "java(ID.genId())")
    SysUserAccountPO toAddPO(SysUserAccountAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<SysUserAccountPO> toAddPOS(List<SysUserAccountAddCommand> cmds);

    /**
     * userAccountPo.setStatus(0);
     * userAccountPo.setDelFlag(0);
     * userAccountPo.setAppType(AppTypeEnum.MANAGER_BFF.name());
     * userAccountPo.setType(1);
     * <p>
     * 第三方绑定信息转换为新增参数。仅限管理端普通账号
     * 新增时删除状态默认为正常。
     *
     * @param event
     * @return
     */
    @Mapping(target = "status", constant = "0")
    @Mapping(target = "delFlag", constant = "false")
    @Mapping(target = "appType", expression = "java(AppTypeEnum.MANAGER_BFF.name())")
    @Mapping(target = "type", constant = "1")
    SysUserAccountPO toOrdinaryAccountAddPO(TripartiteBoundEvent event);

    SysUserAccountDTO toSysUserAccountDTO(SysUserAccountPO sysUserAccountPO);
}
