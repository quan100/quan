package cn.javaquan.app.mobile.bff.article.feign.fallback;

import cn.javaquan.tools.notice.SystemNoticeException;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.HotArticleQuery;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.mobile.bff.article.feign.ArticleServiceFeign;
import cn.javaquan.common.base.message.Result;
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
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<ArticleDTO> getArticle(String articleId) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result hotCategoryArticle(HotArticleQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result byCategory(OpenArticleQuery query) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
