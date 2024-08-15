package cn.javaquan.app.service.chat.service;

import cn.javaquan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.feign.ChatUserFriendGroupRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户好友分组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatUserFriendGroupService {

    private final ChatUserFriendGroupRepositoryFeign chatUserFriendGroupRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ChatUserFriendGroupDTO>> page(ChatUserFriendGroupQuery query) {
        return chatUserFriendGroupRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<ChatUserFriendGroupDTO> details(Long id) {
        return chatUserFriendGroupRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ChatUserFriendGroupUpdateCommand cmd) {
        return chatUserFriendGroupRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ChatUserFriendGroupAddCommand cmd) {
        return chatUserFriendGroupRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<ChatUserFriendGroupAddCommand> cmds) {
        return chatUserFriendGroupRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatUserFriendGroupRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取用户好友分组信息.
     * @param userId 用户id
     * @return 用户好友分组信息
     */
    public Result<List<ChatUserFriendGroupDTO>> queryByUserId(String userId) {
        return chatUserFriendGroupRepositoryFeign.queryByUserId(userId);
    }

}
