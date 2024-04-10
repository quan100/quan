package cn.javaquan.app.core.comment.artalk.repository.impl;

import cn.javaquan.app.core.comment.artalk.entity.ArtalkCommentPagesPO;
import cn.javaquan.app.core.comment.artalk.mapper.ArtalkCommentPagesMapper;
import cn.javaquan.app.core.comment.artalk.repository.ArtalkCommentPagesRepository;
import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
@DS("artalk")
@Repository
public class ArtalkCommentPagesRepositoryImpl extends ServiceImpl<ArtalkCommentPagesMapper, ArtalkCommentPagesPO> implements ArtalkCommentPagesRepository {

    @Override
    public PageResult<ArtalkCommentPagesPO> page(ArtalkCommentPagesPO po, BasePage basePage) {
        Page<ArtalkCommentPagesPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ArtalkCommentPagesPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public List<ArtalkCommentPagesPO> statistics(List<String> keys) {
        LambdaQueryWrapper<ArtalkCommentPagesPO> queryWrapper = Wrappers.lambdaQuery(ArtalkCommentPagesPO.class)
                .in(ArtalkCommentPagesPO::getKey, keys);
        return this.list(queryWrapper);
    }

}
