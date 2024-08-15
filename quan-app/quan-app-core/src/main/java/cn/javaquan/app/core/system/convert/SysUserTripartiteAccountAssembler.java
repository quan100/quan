package cn.javaquan.app.core.system.convert;

import cn.javaquan.app.common.module.auth.TripartiteBoundEvent;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.core.system.entity.SysUserTripartiteAccountPO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户第三方账户参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface SysUserTripartiteAccountAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    SysUserTripartiteAccountAssembler INSTANCE = Mappers.getMapper(SysUserTripartiteAccountAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    SysUserTripartiteAccountPO toQueryPO(SysUserTripartiteAccountQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新自动配置更新时间。 更新时不处理删除状态，删除状态交由删除功能处理。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", ignore = true)
    @Mapping(target = "bindStatus", expression = "java(toBindStatus(cmd.getUserId()))")
    SysUserTripartiteAccountPO toUpdatePO(SysUserTripartiteAccountUpdateCommand cmd);

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
    @Mapping(target = "bindStatus", expression = "java(toBindStatus(cmd.getUserId()))")
    SysUserTripartiteAccountPO toAddPO(SysUserTripartiteAccountAddCommand cmd);

    /**
     * 根据用户ID转换为绑定状态.
     * <p>
     * 当用户ID为空时，返回0，否则返回1
     * @param userId 用户id
     * @return 绑定状态，0：未绑定，1：已绑定
     */
    @Named("toBindStatus")
    default Integer toBindStatus(String userId) {
        return Validate.isBlank(userId) ? 0 : 1;
    }

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<SysUserTripartiteAccountPO> toAddPOS(List<SysUserTripartiteAccountAddCommand> cmds);

    /**
     * 转换为新增参数.
     * @param event 绑定参数
     * @return 新增参数
     */
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", constant = "false")
    SysUserTripartiteAccountPO toAddPO(TripartiteBoundEvent event);

}
