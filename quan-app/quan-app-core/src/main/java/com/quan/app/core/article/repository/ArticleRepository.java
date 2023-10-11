package com.quan.app.core.article.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.app.common.module.article.*;
import com.quan.app.common.module.article.*;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.article.entity.ArticlePO;

import java.util.List;

/**
 * 文章
 *
 * @author wangquan
 * @version 1.0.0
 * @date 2023-01-03 21:31:47
 */
public interface ArticleRepository extends IService<ArticlePO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ArticlePO> page(ArticlePO po, BasePage basePage);

    /**
     * 根据文章ID查询
     *
     * @param articleId
     * @return
     */
    ArticlePO getArticle(String articleId);

    List<ArticlePO> hotCategoryArticle(List<String> articleIds);

    /**
     * 获取站点地图
     * 文章部分跳转页面URL
     *
     * @return
     */
    List<ArticlePO> getSitemaps();

    /**
     * 根据分类查询文章
     *
     * @param query
     * @return
     */
    PageResult<ArticleByCategoryDTO> byCategory(OpenArticleQuery query);

    /**
     * 添加文章
     * 同时添加关联信息
     *
     * @param cmd
     * @return
     */
    ArticlePO saveArticle(ArticleAddCommand cmd);

    /**
     * 添加文章
     * 同时添加关联信息
     *
     * @param cmd
     * @return
     */
    boolean updateArticle(ArticleUpdateCommand cmd);

    ArticleDTO details(Long id);

}

