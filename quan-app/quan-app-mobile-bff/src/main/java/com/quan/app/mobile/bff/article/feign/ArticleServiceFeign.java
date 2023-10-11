package com.quan.app.mobile.bff.article.feign;

import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.common.module.article.*;
import com.quan.app.mobile.bff.article.feign.fallback.ArticleServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 文章
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ArticleServiceFallback.class)
public interface ArticleServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/article/page")
    Result<PageResult<ArticleDTO>> page(@SpringQueryMap ArticleQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/article/details")
    Result<ArticleDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据文章ID查询
     *
     * @param articleId
     * @return
     */
    @GetMapping("/service/article/article")
    Result<ArticleDTO> getArticle(@RequestParam(value = "articleId") String articleId);

    /**
     * 查询分类下最新文章列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/article/hotCategoryArticle")
    Result<List<ArticleDTO>> hotCategoryArticle(@SpringQueryMap HotArticleQuery query);

    /**
     * 根据分类查询文章
     *
     * @param query
     * @return
     */
    @GetMapping("/service/article/byCategory")
    Result<PageResult<ArticleByCategoryDTO>> byCategory(@SpringQueryMap OpenArticleQuery query);

}
