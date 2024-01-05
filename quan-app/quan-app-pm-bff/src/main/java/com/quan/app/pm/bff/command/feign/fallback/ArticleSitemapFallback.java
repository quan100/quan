package com.quan.app.pm.bff.command.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.article.ArticleDTO;
import com.quan.app.pm.bff.command.feign.ArticleSitemapFeign;
import com.quan.common.base.message.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Component
public class ArticleSitemapFallback implements FallbackFactory<ArticleSitemapFeign> {

    @Override
    public ArticleSitemapFeign create(Throwable throwable) {
        return new ArticleSitemapFeign() {

            @Override
            public Result<List<ArticleDTO>> getSitemaps() {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
