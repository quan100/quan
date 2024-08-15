package cn.javaquan.app.core.article.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.article.entity.ArticleCategoryPO;

import java.util.List;

/**
 * 文章分类.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface ArticleCategoryRepository extends IService<ArticleCategoryPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ArticleCategoryPO> page(ArticleCategoryPO po, BasePage basePage);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    boolean deleteByIds(List<Long> ids);

    /**
     * 修改文章分类.
     * @param articleId 文章id
     * @param categoryIdList 分类id列表
     * @return 修改结果
     */
    boolean articleCategoryUpdate(String articleId, List<String> categoryIdList);

}
