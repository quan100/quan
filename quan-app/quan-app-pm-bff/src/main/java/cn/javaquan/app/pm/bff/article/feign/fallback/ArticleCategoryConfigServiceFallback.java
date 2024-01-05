package cn.javaquan.app.pm.bff.article.feign.fallback;

import cn.javaquan.tools.notice.SystemNoticeException;
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
 * 文章分类配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ArticleCategoryConfigServiceFallback implements FallbackFactory<ArticleCategoryConfigServiceFeign> {

    @Override
    public ArticleCategoryConfigServiceFeign create(Throwable throwable) {
        return new ArticleCategoryConfigServiceFeign() {
            @Override
            public Result page(ArticleCategoryConfigQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(ArticleCategoryConfigUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(ArticleCategoryConfigAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<ArticleCategoryConfigAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
