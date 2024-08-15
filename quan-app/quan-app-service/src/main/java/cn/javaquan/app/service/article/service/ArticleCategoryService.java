package cn.javaquan.app.service.article.service;

import cn.javaquan.app.common.module.article.ArticleCategoryAddCommand;
import cn.javaquan.app.common.module.article.ArticleCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.article.feign.ArticleCategoryRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章分类.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ArticleCategoryService {

    private final ArticleCategoryRepositoryFeign articleCategoryRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ArticleCategoryDTO>> page(ArticleCategoryQuery query) {
        return articleCategoryRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<ArticleCategoryDTO> details(Long id) {
        return articleCategoryRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ArticleCategoryUpdateCommand cmd) {
        return articleCategoryRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ArticleCategoryAddCommand cmd) {
        return articleCategoryRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<ArticleCategoryAddCommand> cmds) {
        return articleCategoryRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return articleCategoryRepositoryFeign.deleteByIds(ids);
    }

}
