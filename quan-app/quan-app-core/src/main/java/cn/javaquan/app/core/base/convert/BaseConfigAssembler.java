package cn.javaquan.app.core.base.convert;

import cn.javaquan.app.core.base.entity.BaseConfigPO;
import cn.javaquan.app.common.module.base.BaseConfigAddCommand;
import cn.javaquan.app.common.module.base.BaseConfigQuery;
import cn.javaquan.app.common.module.base.BaseConfigUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统通用配置参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface BaseConfigAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    BaseConfigAssembler INSTANCE = Mappers.getMapper(BaseConfigAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    BaseConfigPO toQueryPO(BaseConfigQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新自动配置更新时间。 更新时不处理删除状态，删除状态交由删除功能处理。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    BaseConfigPO toUpdatePO(BaseConfigUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    BaseConfigPO toAddPO(BaseConfigAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<BaseConfigPO> toAddPOS(List<BaseConfigAddCommand> cmds);

}
