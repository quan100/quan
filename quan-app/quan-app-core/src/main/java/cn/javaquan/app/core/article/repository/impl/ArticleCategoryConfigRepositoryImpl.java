package cn.javaquan.app.core.article.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.common.base.constant.CommonConstant;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.article.entity.ArticleCategoryConfigPO;
import cn.javaquan.app.core.article.mapper.ArticleCategoryConfigMapper;
import cn.javaquan.app.core.article.repository.ArticleCategoryConfigRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章分类配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Repository
public class ArticleCategoryConfigRepositoryImpl extends
        ServiceImpl<ArticleCategoryConfigMapper, ArticleCategoryConfigPO> implements ArticleCategoryConfigRepository {

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
        queryWrapper.eq(ArticleCategoryConfigPO::getDelFlag, CommonConstant.FALSE);
        return this.list(queryWrapper);
    }

    @Override
    public List<ArticleCategoryConfigPO> queryByCategoryList(List<String> categoryIdList) {
        LambdaQueryWrapper<ArticleCategoryConfigPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(ArticleCategoryConfigPO::getCategoryId, categoryIdList);
        queryWrapper.eq(ArticleCategoryConfigPO::getDelFlag, CommonConstant.FALSE);
        return this.list(queryWrapper);
    }

}
