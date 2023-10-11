package com.quan.app.mobile.bff.article.feign.fallback;

import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.article.feign.ArticleContentServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

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
                return Result.fail(throwable.getMessage());
            }

        };
    }
}
