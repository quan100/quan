package com.quan.app.pm.bff.article.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.article.ArticleContentAddCommand;
import com.quan.app.common.module.article.ArticleContentUpdateCommand;
import com.quan.app.pm.bff.article.feign.ArticleContentServiceFeign;
import com.quan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章内容
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ArticleContentServiceFallback implements FallbackFactory<ArticleContentServiceFeign> {

    @Override
    public ArticleContentServiceFeign create(Throwable throwable) {
        return new ArticleContentServiceFeign() {

            @Override
            public Result details(String articleId) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(ArticleContentUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(ArticleContentAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List ids) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
