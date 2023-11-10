package com.quan.app.service.chat.service;

import com.quan.app.common.module.chat.*;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.chat.feign.ChatUserFriendGroupRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户好友分组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatUserFriendGroupService {

    private final ChatUserFriendGroupRepositoryFeign chatUserFriendGroupRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ChatUserFriendGroupDTO>> page(ChatUserFriendGroupQuery query) {
        return chatUserFriendGroupRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ChatUserFriendGroupDTO> details(Long id) {
        return chatUserFriendGroupRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ChatUserFriendGroupUpdateCommand cmd) {
        return chatUserFriendGroupRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ChatUserFriendGroupAddCommand cmd) {
        return chatUserFriendGroupRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ChatUserFriendGroupAddCommand> cmds) {
        return chatUserFriendGroupRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatUserFriendGroupRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取用户好友分组信息
     *
     * @param userId
     * @return
     */
    public Result<List<ChatUserFriendGroupDTO>> queryByUserId(String userId) {
        return chatUserFriendGroupRepositoryFeign.queryByUserId(userId);
    }
}