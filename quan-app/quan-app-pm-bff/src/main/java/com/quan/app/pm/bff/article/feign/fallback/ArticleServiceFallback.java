package com.quan.app.pm.bff.article.feign.fallback;

import com.quan.app.common.module.article.ArticleAddCommand;
import com.quan.app.common.module.article.ArticleQuery;
import com.quan.app.common.module.article.ArticleUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.article.feign.ArticleServiceFeign;
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
        };
    }
}
