package com.quan.app.core.article.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.article.entity.ArticleCategoryConfigPO;
import com.quan.app.core.article.mapper.ArticleCategoryConfigMapper;
import com.quan.app.core.article.repository.ArticleCategoryConfigRepository;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章分类配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:40
 */
@Repository
public class ArticleCategoryConfigRepositoryImpl extends ServiceImpl<ArticleCategoryConfigMapper, ArticleCategoryConfigPO> implements ArticleCategoryConfigRepository {

    @Override
    public PageResult<ArticleCategoryConfigPO> page(ArticleCategoryConfigPO po, BasePage basePage) {
        Page<ArticleCategoryConfigPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ArticleCategoryConfigPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public List<ArticleCategoryConfigPO> queryByArticleId(String articleId) {
        LambdaQueryWrapper<ArticleCategoryConfigPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ArticleCategoryConfigPO::getArticleId, articleId);
        queryWrapper.eq(ArticleCategoryConfigPO::getDelFlag, 0);
        return this.list(queryWrapper);
    }

    @Override
    public List<ArticleCategoryConfigPO> queryByCategoryList(List<String> categoryIdList) {
        LambdaQueryWrapper<ArticleCategoryConfigPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(ArticleCategoryConfigPO::getCategoryId, categoryIdList);
        queryWrapper.eq(ArticleCategoryConfigPO::getDelFlag, 0);
        return this.list(queryWrapper);
    }

}
