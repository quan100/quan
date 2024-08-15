package cn.javaquan.app.pm.bff.command.feign;

import cn.javaquan.app.pm.bff.command.feign.fallback.ArticleSitemapFallback;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ArticleSitemapFallback.class)
public interface ArticleSitemapFeign {

    /**
     * 获取站点地图 文章跳转页面数据.
     * @return 站点地图配置数据
     */
    @GetMapping("/service/article/sitemaps")
    Result<List<ArticleDTO>> getSitemaps();

}
