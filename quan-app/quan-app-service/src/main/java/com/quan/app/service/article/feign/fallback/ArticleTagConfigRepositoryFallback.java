package com.quan.app.service.article.feign.fallback;

import com.quan.app.common.module.article.ArticleTagConfigAddCommand;
import com.quan.app.common.module.article.ArticleTagConfigQuery;
import com.quan.app.common.module.article.ArticleTagConfigUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.ArticleTagConfigRepositoryFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章标签配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ArticleTagConfigRepositoryFallback implements FallbackFactory<ArticleTagConfigRepositoryFeign> {

    @Override
    public ArticleTagConfigRepositoryFeign create(Throwable throwable) {
        return new ArticleTagConfigRepositoryFeign() {
            @Override
            public Result page(ArticleTagConfigQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ArticleTagConfigUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ArticleTagConfigAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ArticleTagConfigAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
