package com.quan.app.core.chat.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import com.quan.app.core.chat.entity.ChatHistoryPO;
import com.quan.app.core.chat.mapper.ChatHistoryMapper;
import com.quan.app.core.chat.repository.ChatHistoryRepository;
import org.springframework.stereotype.Repository;

/**
 * 聊天记录表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Repository
public class ChatHistoryRepositoryImpl extends ServiceImpl<ChatHistoryMapper, ChatHistoryPO> implements ChatHistoryRepository {

    @Override
    public PageResult<ChatHistoryPO> page(ChatHistoryPO po, BasePage basePage) {
        Page<ChatHistoryPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ChatHistoryPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

}
