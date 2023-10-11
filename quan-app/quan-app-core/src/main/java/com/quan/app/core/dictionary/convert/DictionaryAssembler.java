package com.quan.app.core.dictionary.convert;

import com.quan.app.common.module.dictionary.DictionaryAddCommand;
import com.quan.app.common.module.dictionary.DictionaryQuery;
import com.quan.app.common.module.dictionary.DictionaryUpdateCommand;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.app.core.dictionary.entity.DictionaryPO;
import com.quan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface DictionaryAssembler {

    DictionaryAssembler INSTANCE = Mappers.getMapper(DictionaryAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    DictionaryPO toQueryPO(DictionaryQuery query);

    /**
     * 转换为更新参数
     * <p>
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    DictionaryPO toUpdatePO(DictionaryUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    DictionaryPO toAddPO(DictionaryAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<DictionaryPO> toAddPOS(List<DictionaryAddCommand> cmds);
}
