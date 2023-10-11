package com.quan.app.core.friendly.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import com.quan.app.core.friendly.entity.FriendlyLinkPO;
import com.quan.app.core.friendly.mapper.FriendlyLinkMapper;
import com.quan.app.core.friendly.repository.FriendlyLinkRepository;
import org.springframework.stereotype.Repository;

/**
 * 友情链接
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
@Repository
public class FriendlyLinkRepositoryImpl extends ServiceImpl<FriendlyLinkMapper, FriendlyLinkPO> implements FriendlyLinkRepository {

    @Override
    public PageResult<FriendlyLinkPO> page(FriendlyLinkPO po, BasePage basePage) {
        Page<FriendlyLinkPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<FriendlyLinkPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

}
