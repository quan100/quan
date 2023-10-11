package com.quan.app.service.article.service;

import com.quan.app.common.module.article.ArticleTagAddCommand;
import com.quan.app.common.module.article.ArticleTagDTO;
import com.quan.app.common.module.article.ArticleTagQuery;
import com.quan.app.common.module.article.ArticleTagUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.ArticleTagRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章标签
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@Component
public class ArticleTagService {

    private final ArticleTagRepositoryFeign articleTagRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ArticleTagDTO>> page(ArticleTagQuery query) {
        return articleTagRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ArticleTagDTO> details(Long id) {
        return articleTagRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ArticleTagUpdateCommand cmd) {
        return articleTagRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ArticleTagAddCommand cmd) {
        return articleTagRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ArticleTagAddCommand> cmds) {
        return articleTagRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return articleTagRepositoryFeign.deleteByIds(ids);
    }
}