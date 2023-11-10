package com.quan.app.core.chat.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import com.quan.app.core.chat.entity.ChatUserFriendGroupPO;
import com.quan.app.core.chat.mapper.ChatUserFriendGroupMapper;
import com.quan.app.core.chat.repository.ChatUserFriendGroupRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户好友分组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Repository
public class ChatUserFriendGroupRepositoryImpl extends ServiceImpl<ChatUserFriendGroupMapper, ChatUserFriendGroupPO> implements ChatUserFriendGroupRepository {

    @Override
    public PageResult<ChatUserFriendGroupPO> page(ChatUserFriendGroupPO po, BasePage basePage) {
        Page<ChatUserFriendGroupPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ChatUserFriendGroupPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public List<ChatUserFriendGroupPO> queryByUserId(String userId) {
        LambdaQueryWrapper<ChatUserFriendGroupPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ChatUserFriendGroupPO::getUserId, userId);
        return this.list(queryWrapper);
    }

}
