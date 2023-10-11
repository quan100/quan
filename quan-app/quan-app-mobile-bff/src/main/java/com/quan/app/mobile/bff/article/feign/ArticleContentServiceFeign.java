package com.quan.app.mobile.bff.article.feign;

import com.quan.app.common.module.article.ArticleContentDTO;
import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.article.feign.fallback.ArticleContentServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章内容
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ArticleContentServiceFallback.class)
public interface ArticleContentServiceFeign {

    /**
     * 根据ID查询
     *
     * @param articleId
     * @return
     */
    @GetMapping("/service/article/content/details")
    Result<ArticleContentDTO> details(@RequestParam(value = "articleId") String articleId);

}
