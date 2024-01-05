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
 * 用户第三方账户参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface SysUserTripartiteAccountAssembler {

    SysUserTripartiteAccountAssembler INSTANCE = Mappers.getMapper(SysUserTripartiteAccountAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    SysUserTripartiteAccountPO toQueryPO(SysUserTripartiteAccountQuery query);

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
    @Mapping(target = "bindStatus", expression = "java(toBindStatus(cmd.getUserId()))")
    SysUserTripartiteAccountPO toUpdatePO(SysUserTripartiteAccountUpdateCommand cmd);

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
    @Mapping(target = "bindStatus", expression = "java(toBindStatus(cmd.getUserId()))")
    SysUserTripartiteAccountPO toAddPO(SysUserTripartiteAccountAddCommand cmd);

    @Named("toBindStatus")
    default Integer toBindStatus(String userId) {
        return Validate.isBlank(userId) ? 0 : 1;
    }

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<SysUserTripartiteAccountPO> toAddPOS(List<SysUserTripartiteAccountAddCommand> cmds);

    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", constant = "false")
    SysUserTripartiteAccountPO toAddPO(TripartiteBoundEvent event);

}
