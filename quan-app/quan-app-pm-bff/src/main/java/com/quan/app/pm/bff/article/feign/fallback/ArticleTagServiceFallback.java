package com.quan.app.pm.bff.article.feign.fallback;

import com.quan.app.common.module.article.ArticleTagAddCommand;
import com.quan.app.common.module.article.ArticleTagQuery;
import com.quan.app.common.module.article.ArticleTagUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.article.feign.ArticleTagServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章标签
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ArticleTagServiceFallback implements FallbackFactory<ArticleTagServiceFeign> {

    @Override
    public ArticleTagServiceFeign create(Throwable throwable) {
        return new ArticleTagServiceFeign() {
            @Override
            public Result page(ArticleTagQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ArticleTagUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ArticleTagAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ArticleTagAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
