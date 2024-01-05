package cn.javaquan.app.service.chat.service;

import cn.javaquan.app.common.module.chat.ChatUserGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserGroupUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.feign.ChatUserGroupRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 用户群组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatUserGroupService {

    private final ChatUserGroupRepositoryFeign chatUserGroupRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ChatUserGroupDTO>> page(ChatUserGroupQuery query) {
        return chatUserGroupRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ChatUserGroupDTO> details(Long id) {
        return chatUserGroupRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ChatUserGroupUpdateCommand cmd) {
        return chatUserGroupRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ChatUserGroupAddCommand cmd) {
        return chatUserGroupRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ChatUserGroupAddCommand> cmds) {
        return chatUserGroupRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatUserGroupRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 根据用户ID查询用户加入的群
     *
     * @param userId
     * @return
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserGroupDTO>> queryByUserId(String userId) {
        return chatUserGroupRepositoryFeign.queryByUserId(userId);
    }
}
