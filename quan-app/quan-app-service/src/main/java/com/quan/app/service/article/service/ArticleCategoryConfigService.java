package com.quan.app.service.article.service;

import com.quan.app.common.module.article.ArticleCategoryConfigAddCommand;
import com.quan.app.common.module.article.ArticleCategoryConfigDTO;
import com.quan.app.common.module.article.ArticleCategoryConfigQuery;
import com.quan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.ArticleCategoryConfigRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章分类配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@Component
public class ArticleCategoryConfigService {

    private final ArticleCategoryConfigRepositoryFeign articleCategoryConfigRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ArticleCategoryConfigDTO>> page(ArticleCategoryConfigQuery query) {
        return articleCategoryConfigRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ArticleCategoryConfigDTO> details(Long id) {
        return articleCategoryConfigRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ArticleCategoryConfigUpdateCommand cmd) {
        return articleCategoryConfigRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ArticleCategoryConfigAddCommand cmd) {
        return articleCategoryConfigRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ArticleCategoryConfigAddCommand> cmds) {
        return articleCategoryConfigRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return articleCategoryConfigRepositoryFeign.deleteByIds(ids);
    }
}