package cn.javaquan.app.mobile.bff.article.feign;

import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.HotArticleQuery;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.mobile.bff.article.feign.fallback.ArticleServiceFallback;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ArticleServiceFallback.class)
public interface ArticleServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/article/page")
    Result<PageResult<ArticleDTO>> page(@SpringQueryMap ArticleQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/article/details")
    Result<ArticleDTO> details(@RequestParam Long id);

    /**
     * 根据文章ID查询.
     * @param articleId 文章id
     * @return 文章内容
     */
    @GetMapping("/service/article/article")
    Result<ArticleByCategoryDTO> getArticle(@RequestParam String articleId);

    /**
     * 查询分类下最新文章列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/article/hotCategoryArticle")
    Result<List<ArticleDTO>> hotCategoryArticle(@SpringQueryMap HotArticleQuery query);

    /**
     * 根据分类查询文章.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/article/byCategory")
    Result<PageResult<ArticleByCategoryDTO>> byCategory(@SpringQueryMap OpenArticleQuery query);

}
