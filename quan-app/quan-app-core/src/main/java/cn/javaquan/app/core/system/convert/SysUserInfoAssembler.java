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
 * 用户信息参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface SysUserInfoAssembler {

    SysUserInfoAssembler INSTANCE = Mappers.getMapper(SysUserInfoAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    SysUserInfoPO toQueryPO(SysUserInfoQuery query);

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
    SysUserInfoPO toUpdatePO(SysUserInfoUpdateCommand cmd);

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
    SysUserInfoPO toAddPO(SysUserInfoAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<SysUserInfoPO> toAddPOS(List<SysUserInfoAddCommand> cmds);

    /**
     * 第三方绑定信息转换为平台用户信息
     *
     * @param event
     * @return
     */
    @Mapping(target = "nickName", source = "nick")
    @Mapping(target = "phone", source = "mobile")
    @Mapping(target = "delFlag", constant = "false")
    SysUserInfoPO toAddPO(TripartiteBoundEvent event);
}
