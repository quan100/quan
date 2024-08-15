package cn.javaquan.app.service.article.service;

import cn.javaquan.app.common.module.article.ArticleTagConfigAddCommand;
import cn.javaquan.app.common.module.article.ArticleTagConfigDTO;
import cn.javaquan.app.common.module.article.ArticleTagConfigQuery;
import cn.javaquan.app.common.module.article.ArticleTagConfigUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.article.feign.ArticleTagConfigRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章标签配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ArticleTagConfigService {

    private final ArticleTagConfigRepositoryFeign articleTagConfigRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ArticleTagConfigDTO>> page(ArticleTagConfigQuery query) {
        return articleTagConfigRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<ArticleTagConfigDTO> details(Long id) {
        return articleTagConfigRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ArticleTagConfigUpdateCommand cmd) {
        return articleTagConfigRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ArticleTagConfigAddCommand cmd) {
        return articleTagConfigRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<ArticleTagConfigAddCommand> cmds) {
        return articleTagConfigRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return articleTagConfigRepositoryFeign.deleteByIds(ids);
    }

}
