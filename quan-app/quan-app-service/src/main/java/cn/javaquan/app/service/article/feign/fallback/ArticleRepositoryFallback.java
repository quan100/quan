package cn.javaquan.app.service.article.feign.fallback;

import cn.javaquan.app.common.module.article.ArticleAddCommand;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.ArticleUpdateCommand;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.service.article.feign.ArticleRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ArticleRepositoryFallback implements FallbackFactory<ArticleRepositoryFeign> {

    @Override
    public ArticleRepositoryFeign create(Throwable throwable) {
        return new ArticleRepositoryFeign() {
            @Override
            public Result page(ArticleQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ArticleUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ArticleAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ArticleAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result getArticle(String articleId) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<List<ArticleDTO>> hotCategoryArticle(List<String> articleIds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<List<ArticleDTO>> getSitemaps() {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result byCategory(OpenArticleQuery query) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
