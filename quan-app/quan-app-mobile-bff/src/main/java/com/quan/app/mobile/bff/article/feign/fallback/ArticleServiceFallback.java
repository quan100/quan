package com.quan.app.mobile.bff.article.feign.fallback;

import com.quan.app.common.module.article.ArticleDTO;
import com.quan.app.common.module.article.ArticleQuery;
import com.quan.app.common.module.article.HotArticleQuery;
import com.quan.app.common.module.article.OpenArticleQuery;
import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.article.feign.ArticleServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 文章
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ArticleServiceFallback implements FallbackFactory<ArticleServiceFeign> {

    @Override
    public ArticleServiceFeign create(Throwable throwable) {
        return new ArticleServiceFeign() {
            @Override
            public Result page(ArticleQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<ArticleDTO> getArticle(String articleId) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result hotCategoryArticle(HotArticleQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result byCategory(OpenArticleQuery query) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
