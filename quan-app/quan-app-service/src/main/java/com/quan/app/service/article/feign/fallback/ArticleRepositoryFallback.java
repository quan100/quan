package com.quan.app.service.article.feign.fallback;

import com.quan.app.common.module.article.*;
import com.quan.app.common.module.article.*;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.ArticleRepositoryFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ArticleRepositoryFallback implements FallbackFactory<ArticleRepositoryFeign> {

    @Override
    public ArticleRepositoryFeign create(Throwable throwable) {
        return new ArticleRepositoryFeign() {
            @Override
            public Result page(ArticleQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ArticleUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ArticleAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ArticleAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result getArticle(String articleId) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<List<ArticleDTO>> hotCategoryArticle(List<String> articleIds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<List<ArticleDTO>> getSitemaps() {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result byCategory(OpenArticleQuery query) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
