package com.quan.app.core.base.convert;

import com.quan.app.common.module.base.BaseConfigAddCommand;
import com.quan.app.common.module.base.BaseConfigQuery;
import com.quan.app.common.module.base.BaseConfigUpdateCommand;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.app.core.base.entity.BaseConfigPO;
import com.quan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统通用配置参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:39
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface BaseConfigAssembler {

    BaseConfigAssembler INSTANCE = Mappers.getMapper(BaseConfigAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    BaseConfigPO toQueryPO(BaseConfigQuery query);

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
    BaseConfigPO toUpdatePO(BaseConfigUpdateCommand cmd);

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
    BaseConfigPO toAddPO(BaseConfigAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<BaseConfigPO> toAddPOS(List<BaseConfigAddCommand> cmds);
}
