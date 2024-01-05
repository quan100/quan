package cn.javaquan.app.service.article.feign.fallback;

import cn.javaquan.tools.notice.SystemNoticeException;
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
 * 文章标签
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ArticleTagRepositoryFallback implements FallbackFactory<ArticleTagRepositoryFeign> {

    @Override
    public ArticleTagRepositoryFeign create(Throwable throwable) {
        return new ArticleTagRepositoryFeign() {
            @Override
            public Result page(ArticleTagQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(ArticleTagUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(ArticleTagAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<ArticleTagAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
