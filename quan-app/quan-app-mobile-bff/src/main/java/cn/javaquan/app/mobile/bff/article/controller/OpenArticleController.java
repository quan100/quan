package cn.javaquan.app.mobile.bff.article.controller;

import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryVO;
import cn.javaquan.app.common.module.article.ArticleContentDTO;
import cn.javaquan.app.common.module.article.ArticleContentVO;
import cn.javaquan.app.common.module.article.ArticleTagDTO;
import cn.javaquan.app.common.module.article.ArticleTagQuery;
import cn.javaquan.app.common.module.article.ArticleTagVO;
import cn.javaquan.app.common.module.article.ArticleVO;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.mobile.bff.article.convert.OpenArticleAssembler;
import cn.javaquan.app.mobile.bff.article.feign.ArticleCategoryServiceFeign;
import cn.javaquan.app.mobile.bff.article.feign.ArticleContentServiceFeign;
import cn.javaquan.app.mobile.bff.article.feign.ArticleServiceFeign;
import cn.javaquan.app.mobile.bff.article.feign.ArticleTagServiceFeign;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 博客文章接口.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/article")
public class OpenArticleController {

    private final ArticleServiceFeign articleService;

    private final ArticleContentServiceFeign articleContentService;

    private final ArticleCategoryServiceFeign articleCategoryService;

    private final ArticleTagServiceFeign articleTagService;

    /**
     * 获取详情.
     * @param articleId 文章id
     * @return 文章详情
     */
    @GetMapping("/details")
    public Result<ArticleVO> details(@RequestParam String articleId) {
        Result<ArticleByCategoryDTO> result = articleService.getArticle(articleId);
        ArticleVO articleVo = OpenArticleAssembler.INSTANCE.toArticleVo(result.getData());
        if (null != articleVo) {
            Result<ArticleContentDTO> contentResult = articleContentService.details(articleVo.getArticleId());
            if (contentResult.isData()) {
                articleVo.setContent(contentResult.getData().getContent());
                articleVo.setContentCode(contentResult.getData().getContentCode());
            }
        }
        return Result.success(articleVo);
    }

    /**
     * 获取内容.
     * @param articleId 文章id
     * @return 文章内容
     */
    @GetMapping("/content")
    public Result<ArticleContentVO> content(@RequestParam String articleId) {
        Result<ArticleContentDTO> result = articleContentService.details(articleId);
        ArticleContentVO vo = OpenArticleAssembler.INSTANCE.toArticleContentVO(result.getData());
        return Result.success(vo);
    }

    /**
     * 获取分类.
     * @param query 查询参数
     * @return 分类列表
     */
    @GetMapping("/category")
    public Result<PageResult<ArticleCategoryVO>> category(ArticleCategoryQuery query) {
        Result<PageResult<ArticleCategoryDTO>> result = articleCategoryService.page(query);
        return OpenArticleAssembler.INSTANCE.toResult(result);
    }

    /**
     * 获取标签.
     * @param query 查询参数
     * @return 标签列表
     */
    @GetMapping("/tag")
    public Result<PageResult<ArticleTagVO>> tag(ArticleTagQuery query) {
        Result<PageResult<ArticleTagDTO>> result = articleTagService.page(query);
        return OpenArticleAssembler.INSTANCE.toTagResult(result);
    }

    /**
     * 查询文章列表.
     * @param query 查询参数
     * @return 文章列表
     */
    @GetMapping
    public Result<PageResult<ArticleByCategoryDTO>> getArticle(@Valid OpenArticleQuery query) {
        return articleService.byCategory(query);
    }

}
