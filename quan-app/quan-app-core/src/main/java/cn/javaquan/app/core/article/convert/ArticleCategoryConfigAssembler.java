package cn.javaquan.app.core.article.convert;

import cn.javaquan.app.common.module.article.ArticleCategoryConfigAddCommand;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.core.article.entity.ArticleCategoryConfigPO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章分类配置参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface ArticleCategoryConfigAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    ArticleCategoryConfigAssembler INSTANCE = Mappers.getMapper(ArticleCategoryConfigAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    ArticleCategoryConfigPO toQueryPO(ArticleCategoryConfigQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新自动配置更新时间。 更新时不处理删除状态，删除状态交由删除功能处理。 更新时不处理文章ID，业务主键在新增时配置。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "delFlag", ignore = true)
    @Mapping(target = "articleId", ignore = true)
    ArticleCategoryConfigPO toUpdatePO(ArticleCategoryConfigUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    @Mapping(target = "delFlag", constant = "false")
    ArticleCategoryConfigPO toAddPO(ArticleCategoryConfigAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<ArticleCategoryConfigPO> toAddPOS(List<ArticleCategoryConfigAddCommand> cmds);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param articleId 文章ID
     * @param categoryId 分类ID
     * @return 新增参数
     */
    @Mapping(target = "delFlag", constant = "false")
    ArticleCategoryConfigPO toAddPO(String articleId, String categoryId);

}
