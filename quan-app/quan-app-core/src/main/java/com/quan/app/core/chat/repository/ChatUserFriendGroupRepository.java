package com.quan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.chat.entity.ChatUserFriendGroupPO;

import java.util.List;

/**
 * 用户好友分组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
public interface ChatUserFriendGroupRepository extends IService<ChatUserFriendGroupPO> {

    /**
     * 分页查询
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ChatUserFriendGroupPO> page(ChatUserFriendGroupPO po, BasePage basePage);

    /**
     * 查询用户好友分组信息
     *
     * @param userId
     * @return
     */
    List<ChatUserFriendGroupPO> queryByUserId(String userId);
}

