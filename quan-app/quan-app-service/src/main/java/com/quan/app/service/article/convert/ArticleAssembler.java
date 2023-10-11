package com.quan.app.service.article.convert;

import com.quan.app.common.module.article.ArticleCategoryConfigQuery;
import com.quan.app.common.module.article.HotArticleQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author wangquan
 */
@Mapper
public interface ArticleAssembler {

    ArticleAssembler INSTANCE = Mappers.getMapper(ArticleAssembler.class);

    @Mapping(target = "pageNum", constant = "1")
    ArticleCategoryConfigQuery toArticleCategoryConfigQuery(HotArticleQuery query);

}
