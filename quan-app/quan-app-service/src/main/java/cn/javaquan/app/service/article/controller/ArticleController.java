package cn.javaquan.app.service.article.controller;

import cn.javaquan.app.common.module.article.ArticleAddCommand;
import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.ArticleUpdateCommand;
import cn.javaquan.app.common.module.article.HotArticleQuery;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.article.feign.ArticleRepositoryFeign;
import cn.javaquan.app.service.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/article/")
public class ArticleController {

    private final ArticleService articleService;

    private final ArticleRepositoryFeign articleRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ArticleDTO>> page(ArticleQuery query) {
        return articleService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArticleDTO> details(@RequestParam Long id) {
        return articleService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleUpdateCommand cmd) {
        return articleService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleAddCommand cmd) {
        return articleService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleAddCommand> cmds) {
        return articleService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return articleService.deleteByIds(ids);
    }

    /**
     * 根据文章ID查询.
     * @param articleId 文章id
     * @return 文章内容
     */
    @GetMapping("article")
    public Result<ArticleByCategoryDTO> getArticle(@RequestParam String articleId) {
        return articleRepositoryFeign.getArticle(articleId);
    }

    /**
     * 获取站点地图 文章跳转页面数据.
     * @return 站点地图配置数据
     */
    @GetMapping("sitemaps")
    public Result<List<ArticleDTO>> getSitemaps() {
        return articleRepositoryFeign.getSitemaps();
    }

    /**
     * 查询分类下最新文章列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("hotCategoryArticle")
    public Result<List<ArticleDTO>> hotCategoryArticle(@Valid HotArticleQuery query) {
        return articleService.hotCategoryArticle(query);
    }

    /**
     * 根据分类查询文章.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("byCategory")
    public Result<PageResult<ArticleByCategoryDTO>> byCategory(@Valid OpenArticleQuery query) {
        return articleService.byCategory(query);
    }

}
