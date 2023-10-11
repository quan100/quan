package com.quan.app.core.article.repository;

import com.quan.app.core.article.entity.ArticleContentPO;

import java.util.List;

/**
 * 文章内容
 *
 * @author wangquan
 * @version 1.0.0
 * @date 2023-01-03 21:31:47
 */
public interface ArticleContentRepository {

    boolean updateByArticleId(ArticleContentPO po);

    boolean removeByByArticleIds(List<String> articleIds);

    ArticleContentPO getOne(String articleId);

    boolean save(ArticleContentPO po);
}

