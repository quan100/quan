package cn.javaquan.app.core.friendly.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import cn.javaquan.app.core.friendly.entity.FriendlyLinkPO;
import cn.javaquan.app.core.friendly.mapper.FriendlyLinkMapper;
import cn.javaquan.app.core.friendly.repository.FriendlyLinkRepository;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        queryWrapper.orderByAsc(FriendlyLinkPO::getSort);
        queryWrapper.orderByAsc(FriendlyLinkPO::getCreateTime);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

}
