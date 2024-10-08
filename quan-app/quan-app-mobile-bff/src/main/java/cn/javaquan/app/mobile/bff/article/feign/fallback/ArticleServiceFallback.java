package cn.javaquan.app.mobile.bff.article.feign.fallback;

import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.HotArticleQuery;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.mobile.bff.article.feign.ArticleServiceFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.notify.SystemNotifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ArticleServiceFallback implements FallbackFactory<ArticleServiceFeign> {

    @Override
    public ArticleServiceFeign create(Throwable throwable) {
        return new ArticleServiceFeign() {
            @Override
            public Result page(ArticleQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result getArticle(String articleId) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result hotCategoryArticle(HotArticleQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result byCategory(OpenArticleQuery query) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
