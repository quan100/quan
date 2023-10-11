package com.quan.app.service.article.feign.fallback;

import com.quan.app.common.module.article.ArticleContentAddCommand;
import com.quan.app.common.module.article.ArticleContentUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.ArticleContentRepositoryFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章内容
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ArticleContentRepositoryFallback implements FallbackFactory<ArticleContentRepositoryFeign> {

    @Override
    public ArticleContentRepositoryFeign create(Throwable throwable) {
        return new ArticleContentRepositoryFeign() {

            @Override
            public Result details(String articleId) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ArticleContentUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ArticleContentAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List ids) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
