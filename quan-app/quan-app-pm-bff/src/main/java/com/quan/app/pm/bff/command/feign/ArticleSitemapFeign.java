package com.quan.app.pm.bff.command.feign;

import com.quan.app.common.module.article.ArticleDTO;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.command.feign.fallback.ArticleSitemapFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 文章
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ArticleSitemapFallback.class)
public interface ArticleSitemapFeign {

    /**
     * 获取站点地图
     * 文章跳转页面数据
     *
     * @return
     */
    @GetMapping("/service/article/sitemaps")
    Result<List<ArticleDTO>> getSitemaps();
}
