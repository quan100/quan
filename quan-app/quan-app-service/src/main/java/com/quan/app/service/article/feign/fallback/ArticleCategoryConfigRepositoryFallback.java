package com.quan.app.service.article.feign.fallback;

import com.quan.app.common.module.article.ArticleCategoryConfigAddCommand;
import com.quan.app.common.module.article.ArticleCategoryConfigQuery;
import com.quan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.ArticleCategoryConfigRepositoryFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章分类配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ArticleCategoryConfigRepositoryFallback implements FallbackFactory<ArticleCategoryConfigRepositoryFeign> {

    @Override
    public ArticleCategoryConfigRepositoryFeign create(Throwable throwable) {
        return new ArticleCategoryConfigRepositoryFeign() {
            @Override
            public Result page(ArticleCategoryConfigQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ArticleCategoryConfigUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ArticleCategoryConfigAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ArticleCategoryConfigAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
