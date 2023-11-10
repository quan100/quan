package com.quan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.chat.entity.ChatHistoryPO;

/**
 * 聊天记录表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
public interface ChatHistoryRepository extends IService<ChatHistoryPO> {

    /**
     * 分页查询
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ChatHistoryPO> page(ChatHistoryPO po, BasePage basePage);

}

