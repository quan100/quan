package com.quan.app.core.article.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.article.entity.ArticleCategoryConfigPO;

import java.util.List;

/**
 * 文章分类配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:40
 */
public interface ArticleCategoryConfigRepository extends IService<ArticleCategoryConfigPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ArticleCategoryConfigPO> page(ArticleCategoryConfigPO po, BasePage basePage);

    /**
     * 根据文章ID查询
     *
     * @param articleId
     * @return
     */
    List<ArticleCategoryConfigPO> queryByArticleId(String articleId);

    /**
     * 根据分类ID查询
     *
     * @param categoryIdList
     * @return
     */
    List<ArticleCategoryConfigPO> queryByCategoryList(List<String> categoryIdList);
}

