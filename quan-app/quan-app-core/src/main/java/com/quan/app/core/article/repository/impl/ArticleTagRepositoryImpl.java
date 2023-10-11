package com.quan.app.core.article.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.app.common.util.RunUtil;
import com.quan.app.common.util.Validate;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.article.convert.ArticleTagConfigAssembler;
import com.quan.app.core.article.entity.ArticleTagConfigPO;
import com.quan.app.core.article.entity.ArticleTagPO;
import com.quan.app.core.article.mapper.ArticleTagMapper;
import com.quan.app.core.article.repository.ArticleTagConfigRepository;
import com.quan.app.core.article.repository.ArticleTagRepository;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章标签
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:39
 */
@RequiredArgsConstructor
@Repository
public class ArticleTagRepositoryImpl extends ServiceImpl<ArticleTagMapper, ArticleTagPO> implements ArticleTagRepository {

    private final ArticleTagConfigRepository articleTagConfigRepository;

    @Override
    public PageResult<ArticleTagPO> page(ArticleTagPO po, BasePage basePage) {
        Page<ArticleTagPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ArticleTagPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean articleTagUpdate(String articleId, List<String> tagIdList) {
        if (Validate.isEmpty(tagIdList)) {
            return true;
        }

        List<ArticleTagConfigPO> articleTagConfig = articleTagConfigRepository.queryByArticleId(articleId);

        // 需要新增的数据
        List<ArticleTagConfigPO> insertData = tagIdList.stream().filter(tagId -> (articleTagConfig.parallelStream().noneMatch(tagConfig -> tagConfig.getTagId().equals(tagId))))
                .map(tagId -> {
                    return ArticleTagConfigAssembler.INSTANCE.toAddPO(articleId, tagId);
                })
                .collect(Collectors.toList());
        // 需要删除的数据
        List<Long> removeData = articleTagConfig.stream().filter(tagConfig -> (tagIdList.parallelStream().noneMatch(tagId -> tagConfig.getTagId().equals(tagId))))
                .map(ArticleTagConfigPO::getId)
                .collect(Collectors.toList());

        return RunUtil.doRun(Validate.isNotEmpty(insertData) ? articleTagConfigRepository.saveOrUpdateBatch(insertData) : true, () -> {
            return Validate.isNotEmpty(removeData) ? articleTagConfigRepository.removeByIds(removeData) : true;
        });
    }

}
