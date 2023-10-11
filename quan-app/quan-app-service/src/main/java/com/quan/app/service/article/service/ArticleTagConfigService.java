package com.quan.app.service.article.service;

import com.quan.app.common.module.article.ArticleTagConfigAddCommand;
import com.quan.app.common.module.article.ArticleTagConfigDTO;
import com.quan.app.common.module.article.ArticleTagConfigQuery;
import com.quan.app.common.module.article.ArticleTagConfigUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.ArticleTagConfigRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章标签配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@Component
public class ArticleTagConfigService {

    private final ArticleTagConfigRepositoryFeign articleTagConfigRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ArticleTagConfigDTO>> page(ArticleTagConfigQuery query) {
        return articleTagConfigRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ArticleTagConfigDTO> details(Long id) {
        return articleTagConfigRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ArticleTagConfigUpdateCommand cmd) {
        return articleTagConfigRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ArticleTagConfigAddCommand cmd) {
        return articleTagConfigRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ArticleTagConfigAddCommand> cmds) {
        return articleTagConfigRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return articleTagConfigRepositoryFeign.deleteByIds(ids);
    }
}