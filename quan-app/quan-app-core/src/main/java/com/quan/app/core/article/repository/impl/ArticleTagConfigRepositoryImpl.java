package com.quan.app.core.article.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.article.entity.ArticleTagConfigPO;
import com.quan.app.core.article.mapper.ArticleTagConfigMapper;
import com.quan.app.core.article.repository.ArticleTagConfigRepository;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章标签配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:39
 */
@Repository
public class ArticleTagConfigRepositoryImpl extends ServiceImpl<ArticleTagConfigMapper, ArticleTagConfigPO> implements ArticleTagConfigRepository {

    @Override
    public PageResult<ArticleTagConfigPO> page(ArticleTagConfigPO po, BasePage basePage) {
        Page<ArticleTagConfigPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ArticleTagConfigPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public List<ArticleTagConfigPO> queryByArticleId(String articleId) {
        LambdaQueryWrapper<ArticleTagConfigPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ArticleTagConfigPO::getArticleId, articleId);
        queryWrapper.eq(ArticleTagConfigPO::getDelFlag, 0);
        return this.list(queryWrapper);
    }

    @Override
    public List<ArticleTagConfigPO> queryByTagIdList(List<String> tagIdList) {
        LambdaQueryWrapper<ArticleTagConfigPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(ArticleTagConfigPO::getTagId, tagIdList);
        queryWrapper.eq(ArticleTagConfigPO::getDelFlag, 0);
        return this.list(queryWrapper);
    }

}
