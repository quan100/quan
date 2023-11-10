package com.quan.app.core.chat.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import com.quan.app.core.chat.entity.ChatUserGroupPO;
import com.quan.app.core.chat.mapper.ChatUserGroupMapper;
import com.quan.app.core.chat.repository.ChatUserGroupRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户群组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Repository
public class ChatUserGroupRepositoryImpl extends ServiceImpl<ChatUserGroupMapper, ChatUserGroupPO> implements ChatUserGroupRepository {

    @Override
    public PageResult<ChatUserGroupPO> page(ChatUserGroupPO po, BasePage basePage) {
        Page<ChatUserGroupPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ChatUserGroupPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public List<ChatUserGroupPO> queryByUserId(String userId) {
        LambdaQueryWrapper<ChatUserGroupPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ChatUserGroupPO::getUserId, userId);
        return this.list(queryWrapper);
    }

    @Override
    public List<ChatUserGroupPO> queryByGroupId(String groupId) {
        LambdaQueryWrapper<ChatUserGroupPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ChatUserGroupPO::getGroupId, groupId);
        return this.list(queryWrapper);
    }

}
