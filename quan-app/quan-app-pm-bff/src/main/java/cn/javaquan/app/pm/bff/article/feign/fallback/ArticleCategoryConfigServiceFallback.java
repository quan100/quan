package cn.javaquan.app.pm.bff.article.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigAddCommand;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import cn.javaquan.app.pm.bff.article.feign.ArticleCategoryConfigServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章分类配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ArticleCategoryConfigServiceFallback implements FallbackFactory<ArticleCategoryConfigServiceFeign> {

    @Override
    public ArticleCategoryConfigServiceFeign create(Throwable throwable) {
        return new ArticleCategoryConfigServiceFeign() {
            @Override
            public Result page(ArticleCategoryConfigQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ArticleCategoryConfigUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ArticleCategoryConfigAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ArticleCategoryConfigAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
