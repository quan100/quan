package cn.javaquan.app.core.article.controller;

import cn.javaquan.app.core.article.convert.ArticleAssembler;
import cn.javaquan.app.core.article.entity.ArticlePO;
import cn.javaquan.app.core.article.repository.ArticleRepository;
import cn.javaquan.app.common.module.article.*;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/core/article/")
public class ArticleController {

    private final ArticleRepository articleRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ArticleQuery query) {
        ArticlePO po = ArticleAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ArticleDTO> details(@RequestParam Long id) {
        return Result.success(articleRepository.details(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ArticleUpdateCommand cmd) {
        return Result.success(articleRepository.updateArticle(cmd));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<ArticlePO> save(@RequestBody ArticleAddCommand cmd) {
        ArticlePO po = articleRepository.saveArticle(cmd);
        return Result.success(po);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ArticleAddCommand> cmds) {
        List<ArticlePO> pos = ArticleAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleRepository.removeByIds(ids));
    }

    /**
     * 根据文章ID查询
     *
     * @param articleId
     * @return
     */
    @GetMapping("article")
    public Result getArticle(@RequestParam String articleId) {
        return Result.success(articleRepository.getArticle(articleId));
    }

    /**
     * 根据文章ID查询
     *
     * @param articleIds
     * @return
     */
    @GetMapping("hotCategoryArticle")
    public Result hotCategoryArticle(@RequestParam List<String> articleIds) {
        return Result.success(articleRepository.hotCategoryArticle(articleIds));
    }

    /**
     * 获取站点地图
     * 文章跳转页面数据
     *
     * @return
     */
    @GetMapping("sitemaps")
    public Result<List<ArticlePO>> getSitemaps() {
        return Result.success(articleRepository.getSitemaps());
    }

    /**
     * 根据分类查询文章
     *
     * @param query
     * @return
     */
    @GetMapping("byCategory")
    public Result<PageResult<ArticleByCategoryDTO>> byCategory(OpenArticleQuery query) {
        return Result.success(articleRepository.byCategory(query));
    }

}
