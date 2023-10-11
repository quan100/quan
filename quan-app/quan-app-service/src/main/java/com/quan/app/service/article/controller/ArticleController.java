package com.quan.app.service.article.controller;

import com.quan.app.common.module.article.*;
import com.quan.app.common.module.article.*;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.ArticleRepositoryFeign;
import com.quan.app.service.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 文章
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/article/")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleRepositoryFeign articleRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ArticleDTO>> page(ArticleQuery query) {
        return articleService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ArticleDTO> details(@RequestParam Long id) {
        return articleService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleUpdateCommand cmd) {
        return articleService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleAddCommand cmd) {
        return articleService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleAddCommand> cmds) {
        return articleService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return articleService.deleteByIds(ids);
    }

    /**
     * 根据文章ID查询
     *
     * @param articleId
     * @return
     */
    @GetMapping("article")
    public Result<ArticleDTO> getArticle(@RequestParam String articleId) {
        return articleRepositoryFeign.getArticle(articleId);
    }

    /**
     * 获取站点地图
     * 文章跳转页面数据
     *
     * @return
     */
    @GetMapping("sitemaps")
    public Result<List<ArticleDTO>> getSitemaps() {
        return articleRepositoryFeign.getSitemaps();
    }

    /**
     * 查询分类下最新文章列表
     *
     * @param query
     * @return
     */
    @GetMapping("hotCategoryArticle")
    public Result<List<ArticleDTO>> hotCategoryArticle(@Valid HotArticleQuery query) {
        return articleService.hotCategoryArticle(query);
    }

    /**
     * 根据分类查询文章
     *
     * @param query
     * @return
     */
    @GetMapping("byCategory")
    public Result<PageResult<ArticleByCategoryDTO>> byCategory(@Valid OpenArticleQuery query) {
        return articleService.byCategory(query);
    }
}
