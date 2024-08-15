package cn.javaquan.app.pm.bff.article.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.article.ArticleContentAddCommand;
import cn.javaquan.app.common.module.article.ArticleContentUpdateCommand;
import cn.javaquan.app.pm.bff.article.feign.ArticleContentServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章内容.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ArticleContentServiceFallback implements FallbackFactory<ArticleContentServiceFeign> {

    @Override
    public ArticleContentServiceFeign create(Throwable throwable) {
        return new ArticleContentServiceFeign() {

            @Override
            public Result details(String articleId) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ArticleContentUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ArticleContentAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List ids) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
