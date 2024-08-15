package cn.javaquan.app.core.article.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.article.entity.ArticleCategoryConfigPO;

import java.util.List;

/**
 * 文章分类配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface ArticleCategoryConfigRepository extends IService<ArticleCategoryConfigPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ArticleCategoryConfigPO> page(ArticleCategoryConfigPO po, BasePage basePage);

    /**
     * 根据文章ID查询.
     * @param articleId 文章ID
     * @return 查询结果
     */
    List<ArticleCategoryConfigPO> queryByArticleId(String articleId);

    /**
     * 根据分类ID查询.
     * @param categoryIdList 分类ID
     * @return 查询结果
     */
    List<ArticleCategoryConfigPO> queryByCategoryList(List<String> categoryIdList);

}
