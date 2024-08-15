package cn.javaquan.app.service.article.feign;

import cn.javaquan.app.common.module.article.ArticleAddCommand;
import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.ArticleUpdateCommand;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.service.article.feign.fallback.ArticleRepositoryFallback;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = ArticleRepositoryFallback.class)
public interface ArticleRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/article/page")
    Result<PageResult<ArticleDTO>> page(@SpringQueryMap ArticleQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/article/details")
    Result<ArticleDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/article/update")
    Result<Boolean> update(@RequestBody ArticleUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/article/save")
    Result<ArticleDTO> save(@RequestBody ArticleAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/article/saveBatch")
    Result saveBatch(@RequestBody List<ArticleAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/article/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据文章ID查询.
     * @param articleId 文章id
     * @return 文章内容
     */
    @GetMapping("/core/article/article")
    Result<ArticleByCategoryDTO> getArticle(@RequestParam String articleId);

    /**
     * 根据文章ID查询.
     * @param articleIds 文章id
     * @return 文章列表
     */
    @GetMapping("/core/article/hotCategoryArticle")
    Result<List<ArticleDTO>> hotCategoryArticle(@RequestParam List<String> articleIds);

    /**
     * 获取站点地图 文章跳转页面数据.
     * @return 站点地图配置数据
     */
    @GetMapping("/core/article/sitemaps")
    Result<List<ArticleDTO>> getSitemaps();

    /**
     * 根据分类查询文章.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/article/byCategory")
    Result<PageResult<ArticleByCategoryDTO>> byCategory(@SpringQueryMap OpenArticleQuery query);

}
