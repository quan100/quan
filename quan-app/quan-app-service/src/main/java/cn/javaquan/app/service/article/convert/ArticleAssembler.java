package cn.javaquan.app.service.article.convert;

import cn.javaquan.app.common.module.article.ArticleCategoryConfigQuery;
import cn.javaquan.app.common.module.article.HotArticleQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 文章数据转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper
public interface ArticleAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    ArticleAssembler INSTANCE = Mappers.getMapper(ArticleAssembler.class);

    /**
     * 热点文章查询参数转换.
     * @param query 查询参数
     * @return 转换后的查询参数
     */
    @Mapping(target = "pageNum", constant = "1")
    ArticleCategoryConfigQuery toArticleCategoryConfigQuery(HotArticleQuery query);

}
