package com.quan.app.core.chat.controller;

import com.quan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import com.quan.app.common.module.chat.ChatUserFriendGroupQuery;
import com.quan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import com.quan.app.core.chat.entity.ChatUserFriendPO;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.core.chat.convert.ChatUserFriendGroupAssembler;
import com.quan.app.core.chat.entity.ChatUserFriendGroupPO;
import com.quan.app.core.chat.repository.ChatUserFriendGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户好友分组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/chat/user/friend/group/")
public class ChatUserFriendGroupController {

    private final ChatUserFriendGroupRepository chatUserFriendGroupRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ChatUserFriendGroupQuery query) {
        ChatUserFriendGroupPO po = ChatUserFriendGroupAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatUserFriendGroupRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(chatUserFriendGroupRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ChatUserFriendGroupUpdateCommand cmd) {
        ChatUserFriendGroupPO po = ChatUserFriendGroupAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatUserFriendGroupRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ChatUserFriendGroupAddCommand cmd) {
        ChatUserFriendGroupPO po = ChatUserFriendGroupAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatUserFriendGroupRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ChatUserFriendGroupAddCommand> cmds) {
        List<ChatUserFriendGroupPO> pos = ChatUserFriendGroupAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatUserFriendGroupRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatUserFriendGroupRepository.removeByIds(ids));
    }

    /**
     * 获取用户好友分组信息
     *
     * @param userId
     * @return
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserFriendGroupPO>> queryByUserId(@RequestParam String userId) {
        return Result.success(chatUserFriendGroupRepository.queryByUserId(userId));
    }
}
