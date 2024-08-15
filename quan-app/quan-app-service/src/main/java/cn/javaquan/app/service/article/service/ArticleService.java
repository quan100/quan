package cn.javaquan.app.service.article.service;

import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.module.article.ArticleAddCommand;
import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigQuery;
import cn.javaquan.app.common.module.article.ArticleContentAddCommand;
import cn.javaquan.app.common.module.article.ArticleContentDTO;
import cn.javaquan.app.common.module.article.ArticleContentUpdateCommand;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.ArticleUpdateCommand;
import cn.javaquan.app.common.module.article.HotArticleQuery;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.article.convert.ArticleAssembler;
import cn.javaquan.app.service.article.feign.ArticleContentRepositoryFeign;
import cn.javaquan.app.service.article.feign.ArticleRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ArticleService {

    private final ArticleRepositoryFeign articleRepositoryFeign;

    private final ArticleContentRepositoryFeign articleContentRepositoryFeign;

    private final ArticleCategoryConfigService articleCategoryConfigService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ArticleDTO>> page(ArticleQuery query) {
        return articleRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * <p>
     * 获取文章详情
     * @param id 主键
     * @return 文章数据
     */
    public Result<ArticleDTO> details(Long id) {
        Result<ArticleDTO> articleDTOResult = articleRepositoryFeign.details(id);
        if (!articleDTOResult.isData()) {
            return articleDTOResult;
        }
        ArticleDTO articleDTO = articleDTOResult.getData();
        Result<ArticleContentDTO> articleContentDTOResult = articleContentRepositoryFeign
            .details(articleDTO.getArticleId());
        if (articleContentDTOResult.isSuccess()) {
            articleDTO.setContent(articleContentDTOResult.getData());
        }
        return articleDTOResult;
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ArticleUpdateCommand cmd) {
        Result<ArticleDTO> updateBefore = articleRepositoryFeign.details(cmd.getId());
        Validate.isTrue(updateBefore.isData(), ErrorCodeEnum.DATA_NOT_EXIST_ERROR);

        return RunUtil.doRun(articleRepositoryFeign.update(cmd), () -> {
            ArticleContentUpdateCommand content = cmd.getContent();
            if (null != content) {
                content.setArticleId(updateBefore.getData().getArticleId());
                return articleContentRepositoryFeign.update(content);
            }
            return Result.success();
        });
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ArticleAddCommand cmd) {
        Result<ArticleDTO> result = articleRepositoryFeign.save(cmd);
        return RunUtil.doRun(result, () -> {
            ArticleContentAddCommand content = cmd.getContent();
            content.setArticleId(result.getData().getArticleId());
            return articleContentRepositoryFeign.save(content);
        });
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<ArticleAddCommand> cmds) {
        return articleRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return articleRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 查询最新文章列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<List<ArticleDTO>> hotCategoryArticle(HotArticleQuery query) {
        ArticleCategoryConfigQuery categoryConfigQuery = ArticleAssembler.INSTANCE.toArticleCategoryConfigQuery(query);
        Result<PageResult<ArticleCategoryConfigDTO>> result = articleCategoryConfigService.page(categoryConfigQuery);
        if (!result.isData()) {
            return Result.success(Collections.emptyList());
        }
        PageResult<ArticleCategoryConfigDTO> pageResult = result.getData();
        List<ArticleCategoryConfigDTO> categoryConfig = pageResult.getRecords();
        if (Validate.isEmpty(categoryConfig)) {
            return Result.success(Collections.emptyList());
        }

        return articleRepositoryFeign.hotCategoryArticle(
                categoryConfig.stream().map(ArticleCategoryConfigDTO::getArticleId).collect(Collectors.toList()));
    }

    /**
     * 根据分类查询文章.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ArticleByCategoryDTO>> byCategory(OpenArticleQuery query) {
        return articleRepositoryFeign.byCategory(query);
    }

}
