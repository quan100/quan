package cn.javaquan.app.service.article.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.article.ArticleTagAddCommand;
import cn.javaquan.app.common.module.article.ArticleTagQuery;
import cn.javaquan.app.common.module.article.ArticleTagUpdateCommand;
import cn.javaquan.app.service.article.feign.ArticleTagRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章标签.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ArticleTagRepositoryFallback implements FallbackFactory<ArticleTagRepositoryFeign> {

    @Override
    public ArticleTagRepositoryFeign create(Throwable throwable) {
        return new ArticleTagRepositoryFeign() {
            @Override
            public Result page(ArticleTagQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ArticleTagUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ArticleTagAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ArticleTagAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
