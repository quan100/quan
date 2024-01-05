package cn.javaquan.app.core.article.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.article.convert.ArticleCategoryConfigAssembler;
import cn.javaquan.app.core.article.entity.ArticleCategoryConfigPO;
import cn.javaquan.app.core.article.entity.ArticleCategoryPO;
import cn.javaquan.app.core.article.mapper.ArticleCategoryMapper;
import cn.javaquan.app.core.article.repository.ArticleCategoryConfigRepository;
import cn.javaquan.app.core.article.repository.ArticleCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章分类
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:40
 */
@RequiredArgsConstructor
@Repository
public class ArticleCategoryRepositoryImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategoryPO> implements ArticleCategoryRepository {

    private final ArticleCategoryConfigRepository articleCategoryConfigRepository;

    @Override
    public PageResult<ArticleCategoryPO> page(ArticleCategoryPO po, BasePage basePage) {
        Page<ArticleCategoryPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ArticleCategoryPO> queryWrapper = Wrappers.lambdaQuery(po);
        queryWrapper.orderByAsc(ArticleCategoryPO::getSort);
        queryWrapper.orderByDesc(ArticleCategoryPO::getCreateTime);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteByIds(List<Long> ids) {
        List<ArticleCategoryPO> articleCategoryPOS = this.listByIds(ids);
        if (Validate.isEmpty(articleCategoryPOS)) {
            return true;
        }

        List<String> categoryIdList = articleCategoryPOS.stream().map(ArticleCategoryPO::getCategoryId).collect(Collectors.toList());

        return RunUtil.doRun(this.removeByIds(ids), () -> {
            List<ArticleCategoryConfigPO> categoryConfigPOS = articleCategoryConfigRepository.queryByCategoryList(categoryIdList);
            if (Validate.isEmpty(categoryConfigPOS)) {
                return true;
            }
            return articleCategoryConfigRepository.removeByIds(categoryConfigPOS.stream().map(ArticleCategoryConfigPO::getId).collect(Collectors.toList()));
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean articleCategoryUpdate(String articleId, List<String> categoryIdList) {
        if (Validate.isEmpty(categoryIdList)) {
            return true;
        }

        List<ArticleCategoryConfigPO> articleCategoryConfig = articleCategoryConfigRepository.queryByArticleId(articleId);

        // 需要新增的数据
        List<ArticleCategoryConfigPO> insertData = categoryIdList.stream().filter(categoryId -> (articleCategoryConfig.parallelStream().noneMatch(categoryConfig -> categoryConfig.getCategoryId().equals(categoryId))))
                .map(categoryId -> {
                    return ArticleCategoryConfigAssembler.INSTANCE.toAddPO(articleId, categoryId);
                })
                .collect(Collectors.toList());
        // 需要删除的数据
        List<Long> removeData = articleCategoryConfig.stream().filter(categoryConfig -> (categoryIdList.parallelStream().noneMatch(categoryId -> categoryConfig.getCategoryId().equals(categoryId))))
                .map(ArticleCategoryConfigPO::getId)
                .collect(Collectors.toList());

        return RunUtil.doRun(Validate.isNotEmpty(insertData) ? articleCategoryConfigRepository.saveOrUpdateBatch(insertData) : true, () -> {
            return Validate.isNotEmpty(removeData) ? articleCategoryConfigRepository.removeByIds(removeData) : true;
        });
    }

}
