package cn.javaquan.app.core.system.convert;

import cn.javaquan.app.common.module.auth.TripartiteBoundEvent;
import cn.javaquan.app.common.module.system.SysUserInfoAddCommand;
import cn.javaquan.app.common.module.system.SysUserInfoQuery;
import cn.javaquan.app.common.module.system.SysUserInfoUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.core.system.entity.SysUserInfoPO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户信息参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface SysUserInfoAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    SysUserInfoAssembler INSTANCE = Mappers.getMapper(SysUserInfoAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    SysUserInfoPO toQueryPO(SysUserInfoQuery query);

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
    SysUserInfoPO toUpdatePO(SysUserInfoUpdateCommand cmd);

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
    SysUserInfoPO toAddPO(SysUserInfoAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<SysUserInfoPO> toAddPOS(List<SysUserInfoAddCommand> cmds);

    /**
     * 第三方绑定信息转换为平台用户信息.
     * @param event 绑定参数
     * @return 新增参数
     */
    @Mapping(target = "nickName", source = "nick")
    @Mapping(target = "phone", source = "mobile")
    @Mapping(target = "delFlag", constant = "false")
    SysUserInfoPO toAddPO(TripartiteBoundEvent event);

}
