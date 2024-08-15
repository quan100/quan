package cn.javaquan.app.core.dictionary.convert;

import cn.javaquan.app.common.module.dictionary.DictionaryAddCommand;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.module.dictionary.DictionaryUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.core.dictionary.entity.DictionaryPO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface DictionaryAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    DictionaryAssembler INSTANCE = Mappers.getMapper(DictionaryAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    DictionaryPO toQueryPO(DictionaryQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    DictionaryPO toUpdatePO(DictionaryUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    DictionaryPO toAddPO(DictionaryAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<DictionaryPO> toAddPOS(List<DictionaryAddCommand> cmds);

}
