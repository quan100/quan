package cn.javaquan.app.service.chat.service;

import cn.javaquan.app.common.module.chat.ChatUserFriendAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendDTO;
import cn.javaquan.app.common.module.chat.ChatUserFriendQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.feign.ChatUserFriendRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户好友信息关联表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatUserFriendService {

    private final ChatUserFriendRepositoryFeign chatUserFriendRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ChatUserFriendDTO>> page(ChatUserFriendQuery query) {
        return chatUserFriendRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ChatUserFriendDTO> details(Long id) {
        return chatUserFriendRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ChatUserFriendUpdateCommand cmd) {
        return chatUserFriendRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ChatUserFriendAddCommand cmd) {
        return chatUserFriendRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ChatUserFriendAddCommand> cmds) {
        return chatUserFriendRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatUserFriendRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取用户好友id
     *
     * @param userId
     * @return
     */
    public Result<List<ChatUserFriendDTO>> queryByUserId(String userId) {
        return chatUserFriendRepositoryFeign.queryByUserId(userId);
    }
}
