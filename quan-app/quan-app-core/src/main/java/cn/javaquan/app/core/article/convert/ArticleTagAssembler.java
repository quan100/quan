package cn.javaquan.app.core.article.convert;

import cn.javaquan.app.common.module.article.ArticleTagAddCommand;
import cn.javaquan.app.common.module.article.ArticleTagQuery;
import cn.javaquan.app.common.module.article.ArticleTagUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.core.article.entity.ArticleTagPO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章标签参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface ArticleTagAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    ArticleTagAssembler INSTANCE = Mappers.getMapper(ArticleTagAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    ArticleTagPO toQueryPO(ArticleTagQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新自动配置更新时间。 更新时不处理删除状态，删除状态交由删除功能处理。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", ignore = true)
    ArticleTagPO toUpdatePO(ArticleTagUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    @Mapping(target = "tagId", expression = "java(ID.genId())")
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", constant = "false")
    ArticleTagPO toAddPO(ArticleTagAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<ArticleTagPO> toAddPOS(List<ArticleTagAddCommand> cmds);

}
