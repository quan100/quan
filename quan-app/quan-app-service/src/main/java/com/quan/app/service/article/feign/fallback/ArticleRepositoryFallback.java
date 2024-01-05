package com.quan.app.service.article.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.article.*;
import com.quan.app.service.article.feign.ArticleRepositoryFeign;
import com.quan.common.base.message.Result;
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
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(ArticleUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(ArticleAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<ArticleAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getArticle(String articleId) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<List<ArticleDTO>> hotCategoryArticle(List<String> articleIds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<List<ArticleDTO>> getSitemaps() {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result byCategory(OpenArticleQuery query) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
