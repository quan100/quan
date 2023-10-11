package com.quan.app.service.article.service;

import com.quan.app.common.module.article.ArticleCategoryAddCommand;
import com.quan.app.common.module.article.ArticleCategoryDTO;
import com.quan.app.common.module.article.ArticleCategoryQuery;
import com.quan.app.common.module.article.ArticleCategoryUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.ArticleCategoryRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章分类
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@Component
public class ArticleCategoryService {

    private final ArticleCategoryRepositoryFeign articleCategoryRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ArticleCategoryDTO>> page(ArticleCategoryQuery query) {
        return articleCategoryRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ArticleCategoryDTO> details(Long id) {
        return articleCategoryRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ArticleCategoryUpdateCommand cmd) {
        return articleCategoryRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ArticleCategoryAddCommand cmd) {
        return articleCategoryRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ArticleCategoryAddCommand> cmds) {
        return articleCategoryRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return articleCategoryRepositoryFeign.deleteByIds(ids);
    }
}