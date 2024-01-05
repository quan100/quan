package cn.javaquan.app.core.friendly.convert;

import cn.javaquan.app.core.friendly.entity.FriendlyLinkPO;
import cn.javaquan.app.common.module.friendly.FriendlyLinkAddCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkQuery;
import cn.javaquan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 友情链接参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface FriendlyLinkAssembler {

    FriendlyLinkAssembler INSTANCE = Mappers.getMapper(FriendlyLinkAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    FriendlyLinkPO toQueryPO(FriendlyLinkQuery query);

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
    FriendlyLinkPO toUpdatePO(FriendlyLinkUpdateCommand cmd);

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
    FriendlyLinkPO toAddPO(FriendlyLinkAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<FriendlyLinkPO> toAddPOS(List<FriendlyLinkAddCommand> cmds);
}
