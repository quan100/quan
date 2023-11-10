package com.quan.app.core.chat.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import com.quan.app.core.chat.entity.ChatUserInfoPO;
import com.quan.app.core.chat.mapper.ChatUserInfoMapper;
import com.quan.app.core.chat.repository.ChatUserInfoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Repository
public class ChatUserInfoRepositoryImpl extends ServiceImpl<ChatUserInfoMapper, ChatUserInfoPO> implements ChatUserInfoRepository {

    @Override
    public PageResult<ChatUserInfoPO> page(ChatUserInfoPO po, BasePage basePage) {
        Page<ChatUserInfoPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ChatUserInfoPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public ChatUserInfoPO queryByUserId(String userId) {
        LambdaQueryWrapper<ChatUserInfoPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ChatUserInfoPO::getUserId, userId);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<ChatUserInfoPO> queryByUserIds(List<String> userIds) {
        LambdaQueryWrapper<ChatUserInfoPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(ChatUserInfoPO::getUserId, userIds);
        return this.list(queryWrapper);
    }

}
