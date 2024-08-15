package cn.javaquan.app.core.article.controller;

import cn.javaquan.app.common.module.article.ArticleAddCommand;
import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.ArticleUpdateCommand;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.core.article.convert.ArticleAssembler;
import cn.javaquan.app.core.article.entity.ArticlePO;
import cn.javaquan.app.core.article.repository.ArticleRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/article/")
public class ArticleController {

    private final ArticleRepository articleRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ArticlePO>> page(ArticleQuery query) {
        ArticlePO po = ArticleAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArticleDTO> details(@RequestParam Long id) {
        return Result.success(articleRepository.details(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleUpdateCommand cmd) {
        return Result.success(articleRepository.updateArticle(cmd));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<ArticlePO> save(@RequestBody ArticleAddCommand cmd) {
        ArticlePO po = articleRepository.saveArticle(cmd);
        return Result.success(po);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleAddCommand> cmds) {
        List<ArticlePO> pos = ArticleAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleRepository.removeByIds(ids));
    }

    /**
     * 根据文章ID查询.
     * @param articleId 文章ID
     * @return 文章信息
     */
    @GetMapping("article")
    public Result<ArticleByCategoryDTO> getArticle(@RequestParam String articleId) {
        return Result.success(articleRepository.getArticle(articleId));
    }

    /**
     * 根据文章ID查询.
     * @param articleIds 文章ID列表
     * @return 文章信息
     */
    @GetMapping("hotCategoryArticle")
    public Result<List<ArticlePO>> hotCategoryArticle(@RequestParam List<String> articleIds) {
        return Result.success(articleRepository.hotCategoryArticle(articleIds));
    }

    /**
     * 获取站点地图 文章跳转页面数据.
     * @return 站点地图配置数据
     */
    @GetMapping("sitemaps")
    public Result<List<ArticlePO>> getSitemaps() {
        return Result.success(articleRepository.getSitemaps());
    }

    /**
     * 根据分类查询文章.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("byCategory")
    public Result<PageResult<ArticleByCategoryDTO>> byCategory(OpenArticleQuery query) {
        return Result.success(articleRepository.byCategory(query));
    }

}
