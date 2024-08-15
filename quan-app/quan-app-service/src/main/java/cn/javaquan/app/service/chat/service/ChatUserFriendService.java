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
 * 用户好友信息关联表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatUserFriendService {

    private final ChatUserFriendRepositoryFeign chatUserFriendRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ChatUserFriendDTO>> page(ChatUserFriendQuery query) {
        return chatUserFriendRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<ChatUserFriendDTO> details(Long id) {
        return chatUserFriendRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ChatUserFriendUpdateCommand cmd) {
        return chatUserFriendRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ChatUserFriendAddCommand cmd) {
        return chatUserFriendRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<ChatUserFriendAddCommand> cmds) {
        return chatUserFriendRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatUserFriendRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取用户好友id.
     * @param userId 用户id
     * @return 用户好友信息
     */
    public Result<List<ChatUserFriendDTO>> queryByUserId(String userId) {
        return chatUserFriendRepositoryFeign.queryByUserId(userId);
    }

}
