package cn.javaquan.app.core.system.convert;

import cn.javaquan.app.common.module.auth.TripartiteBoundEvent;
import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountDTO;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.common.base.constant.AppTypeEnum;
import cn.javaquan.app.core.system.entity.SysUserAccountPO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户账号参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class, AppTypeEnum.class })
public interface SysUserAccountAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    SysUserAccountAssembler INSTANCE = Mappers.getMapper(SysUserAccountAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    @Mapping(target = "account", ignore = true)
    SysUserAccountPO toQueryPO(SysUserAccountQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新自动配置更新时间。 更新时不处理删除状态，删除状态交由删除功能处理。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", ignore = true)
    @Mapping(target = "userId", ignore = true)
    SysUserAccountPO toUpdatePO(SysUserAccountUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", constant = "false")
    @Mapping(target = "userId", expression = "java(ID.genId())")
    SysUserAccountPO toAddPO(SysUserAccountAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<SysUserAccountPO> toAddPOS(List<SysUserAccountAddCommand> cmds);

    /**
     * 第三方绑定信息转换为新增参数.
     * <p>
     * 仅限管理端普通账号新增时删除状态默认为正常。
     * @param event 第三房账号绑定事件参数
     * @return 新增参数
     */
    @Mapping(target = "status", constant = "0")
    @Mapping(target = "delFlag", constant = "false")
    @Mapping(target = "appType", expression = "java(AppTypeEnum.MANAGER_BFF.name())")
    @Mapping(target = "type", constant = "1")
    SysUserAccountPO toOrdinaryAccountAddPO(TripartiteBoundEvent event);

    /**
     * 转换为DTO.
     * @param sysUserAccountPO 用户账号参数
     * @return dto
     */
    SysUserAccountDTO toSysUserAccountDTO(SysUserAccountPO sysUserAccountPO);

}
