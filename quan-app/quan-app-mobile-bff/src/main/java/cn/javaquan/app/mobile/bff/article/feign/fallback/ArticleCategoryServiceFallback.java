package cn.javaquan.app.mobile.bff.article.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.article.ArticleCategoryAddCommand;
import cn.javaquan.app.common.module.article.ArticleCategoryQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryUpdateCommand;
import cn.javaquan.app.mobile.bff.article.feign.ArticleCategoryServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章分类.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ArticleCategoryServiceFallback implements FallbackFactory<ArticleCategoryServiceFeign> {

    @Override
    public ArticleCategoryServiceFeign create(Throwable throwable) {
        return new ArticleCategoryServiceFeign() {
            @Override
            public Result page(ArticleCategoryQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ArticleCategoryUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ArticleCategoryAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ArticleCategoryAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
