package cn.javaquan.app.core.article.repository.impl;

import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.module.article.ArticleAddCommand;
import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleUpdateCommand;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.core.article.convert.ArticleAssembler;
import cn.javaquan.app.core.article.entity.ArticlePO;
import cn.javaquan.app.core.article.entity.ArticleTagConfigPO;
import cn.javaquan.app.core.article.mapper.ArticleMapper;
import cn.javaquan.app.core.article.repository.ArticleRepository;
import cn.javaquan.app.core.article.repository.ArticleTagConfigRepository;
import cn.javaquan.app.core.article.repository.ArticleTagRepository;
import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import cn.javaquan.common.base.constant.CommonConstant;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章 服务实现类.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Repository
public class ArticleRepositoryImpl extends ServiceImpl<ArticleMapper, ArticlePO> implements ArticleRepository {

    private final ArticleMapper articleMapper;

    private final ArticleTagRepository articleTagRepository;

    private final ArticleTagConfigRepository articleTagConfigRepository;

    @Override
    public PageResult<ArticlePO> page(ArticlePO po, BasePage basePage) {
        Page<ArticlePO> page = PageAssembler.INSTANCE.toPage(basePage);
        String title = po.getTitle();
        po.setTitle(null);
        LambdaQueryWrapper<ArticlePO> queryWrapper = Wrappers.lambdaQuery(po);
        queryWrapper.select(ArticlePO::getId, ArticlePO::getArticleId, ArticlePO::getUserId, ArticlePO::getTitle,
                ArticlePO::getAuthor, ArticlePO::getType, ArticlePO::getPublishType, ArticlePO::getStatus,
                ArticlePO::getSort, ArticlePO::getBriefContent, ArticlePO::getCreateTime, ArticlePO::getUpdateTime);

        queryWrapper.like(Validate.isNotBlank(title), ArticlePO::getTitle, title);
        queryWrapper.orderByDesc(ArticlePO::getTopping);
        queryWrapper.orderByAsc(ArticlePO::getSort);
        queryWrapper.orderByDesc(ArticlePO::getCreateTime, ArticlePO::getUpdateTime);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public ArticleByCategoryDTO getArticle(String articleId) {
        OpenArticleQuery query = ArticleAssembler.INSTANCE.toOpenArticleQuery(articleId);
        PageResult<ArticleByCategoryDTO> pageResult = byCategory(query);
        List<ArticleByCategoryDTO> records = pageResult.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return null;
        }
        return records.get(0);
    }

    @Override
    public List<ArticlePO> hotCategoryArticle(List<String> articleIds) {
        LambdaQueryWrapper<ArticlePO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(ArticlePO::getArticleId, articleIds);
        queryWrapper.eq(ArticlePO::getDelFlag, CommonConstant.FALSE);
        queryWrapper.eq(ArticlePO::getStatus, 0);
        queryWrapper.orderByDesc(ArticlePO::getTopping);
        queryWrapper.orderByAsc(ArticlePO::getSort);
        queryWrapper.orderByDesc(ArticlePO::getCreateTime);
        return this.list(queryWrapper);
    }

    @Override
    public List<ArticlePO> getSitemaps() {
        LambdaQueryWrapper<ArticlePO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ArticlePO::getDelFlag, CommonConstant.FALSE);
        queryWrapper.eq(ArticlePO::getStatus, 0);
        queryWrapper.eq(ArticlePO::getJumpType, 1);
        return this.list(queryWrapper);
    }

    @Override
    public PageResult<ArticleByCategoryDTO> byCategory(OpenArticleQuery query) {
        Page page = new Page(query.getPageNum(), query.getPageSize());

        QueryWrapper queryWrapper = Wrappers.query();

        queryWrapper.eq(Validate.isNotBlank(query.getArticleId()), "article.article_id", query.getArticleId());
        queryWrapper.eq(Validate.isNotBlank(query.getCategoryId()), "article.category_id", query.getCategoryId());
        queryWrapper.eq(Validate.isNotBlank(query.getTagId()), "config.tag_id", query.getTagId());
        queryWrapper.eq(Validate.isNotNull(query.getType()), "article.type", query.getType());
        queryWrapper.eq(Validate.isNotNull(query.getJumpType()), "article.jump_type", query.getJumpType());

        queryWrapper.eq("article.del_flag", 0);
        queryWrapper.eq("article.status", 0);
        queryWrapper.eq(Validate.isNotBlank(query.getTagId()), "tag.del_flag", 0);
        queryWrapper.eq(Validate.isNotBlank(query.getTagId()), "config.del_flag", 0);

        queryWrapper.like(Validate.isNotBlank(query.getTitle()), "article.title", query.getTitle());
        queryWrapper.like(Validate.isNotBlank(query.getAuthor()), "article.author", query.getAuthor());
        queryWrapper.like(Validate.isNotBlank(query.getSource()), "article.source", query.getSource());
        queryWrapper.orderByDesc("article.topping");
        queryWrapper.orderByAsc("article.sort");
        queryWrapper.orderByDesc("article.create_time");

        Page<ArticleByCategoryDTO> pageResult = articleMapper.byCategory(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(pageResult);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ArticlePO saveArticle(ArticleAddCommand cmd) {
        ArticlePO po = ArticleAssembler.INSTANCE.toAddPO(cmd);
        List<String> tagIdList = cmd.getTagIdList();
        RunUtil.doRun(this.save(po), () -> {
            return articleTagRepository.articleTagUpdate(po.getArticleId(), tagIdList);
        });
        return po;
    }

    @Override
    public boolean updateArticle(ArticleUpdateCommand cmd) {
        ArticlePO po = this.getById(cmd.getId());
        Validate.isNotNull(po, ErrorCodeEnum.DATA_NOT_EXIST_ERROR);

        ArticlePO updatePO = ArticleAssembler.INSTANCE.toUpdatePO(cmd);

        List<String> tagIdList = cmd.getTagIdList();
        return RunUtil.doRun(this.updateById(updatePO), () -> {
            return articleTagRepository.articleTagUpdate(po.getArticleId(), tagIdList);
        });
    }

    @Override
    public ArticleDTO details(Long id) {
        ArticlePO articlePO = this.getById(id);
        if (null == articlePO) {
            return null;
        }
        ArticleDTO articleDTO = ArticleAssembler.INSTANCE.toArticleDTO(articlePO);
        List<ArticleTagConfigPO> articleTagConfigPOS = articleTagConfigRepository
            .queryByArticleId(articlePO.getArticleId());
        if (Validate.isNotEmpty(articleTagConfigPOS)) {
            articleDTO.setTagIdList(
                    articleTagConfigPOS.stream().map(ArticleTagConfigPO::getTagId).collect(Collectors.toList()));
        }
        return articleDTO;
    }

}
