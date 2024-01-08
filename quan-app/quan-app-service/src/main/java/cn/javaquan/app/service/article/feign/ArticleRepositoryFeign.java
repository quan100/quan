package cn.javaquan.app.service.article.feign;

import cn.javaquan.app.service.article.feign.fallback.ArticleRepositoryFallback;
import cn.javaquan.app.common.module.article.*;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ArticleRepositoryFallback.class)
public interface ArticleRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/article/page")
    Result<PageResult<ArticleDTO>> page(@SpringQueryMap ArticleQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/article/details")
    Result<ArticleDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/article/update")
    Result<Boolean> update(@RequestBody ArticleUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/article/save")
    Result<ArticleDTO> save(@RequestBody ArticleAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/article/saveBatch")
    Result saveBatch(@RequestBody List<ArticleAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/article/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据文章ID查询
     *
     * @param articleId
     * @return
     */
    @GetMapping("/core/article/article")
    Result<ArticleByCategoryDTO> getArticle(@RequestParam(value = "articleId") String articleId);

    /**
     * 根据文章ID查询
     *
     * @param articleIds
     * @return
     */
    @GetMapping("/core/article/hotCategoryArticle")
    Result<List<ArticleDTO>> hotCategoryArticle(@RequestParam List<String> articleIds);

    /**
     * 获取站点地图
     * 文章跳转页面数据
     *
     * @return
     */
    @GetMapping("/core/article/sitemaps")
    Result<List<ArticleDTO>> getSitemaps();

    /**
     * 根据分类查询文章
     *
     * @param query
     * @return
     */
    @GetMapping("/core/article/byCategory")
    Result<PageResult<ArticleByCategoryDTO>> byCategory(@SpringQueryMap OpenArticleQuery query);

}
