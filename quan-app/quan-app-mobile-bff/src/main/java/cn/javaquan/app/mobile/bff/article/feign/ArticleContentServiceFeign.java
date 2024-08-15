package cn.javaquan.app.mobile.bff.article.feign;

import cn.javaquan.app.mobile.bff.article.feign.fallback.ArticleContentServiceFallback;
import cn.javaquan.app.common.module.article.ArticleContentDTO;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章内容.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ArticleContentServiceFallback.class)
public interface ArticleContentServiceFeign {

    /**
     * 根据ID查询.
     * @param articleId 文章id
     * @return 文章内容
     */
    @GetMapping("/service/article/content/details")
    Result<ArticleContentDTO> details(@RequestParam String articleId);

}
