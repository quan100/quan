package com.quan.app.service.chat.controller;

import com.quan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import com.quan.app.common.module.chat.ChatUserFriendGroupDTO;
import com.quan.app.common.module.chat.ChatUserFriendGroupQuery;
import com.quan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.chat.service.ChatUserFriendGroupService;
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
@RequestMapping("/service/chat/user/friend/group/")
public class ChatUserFriendGroupController {

    private final ChatUserFriendGroupService chatUserFriendGroupService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserFriendGroupDTO>> page(ChatUserFriendGroupQuery query) {
        return chatUserFriendGroupService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ChatUserFriendGroupDTO> details(@RequestParam Long id) {
        return chatUserFriendGroupService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserFriendGroupUpdateCommand cmd) {
        return chatUserFriendGroupService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserFriendGroupAddCommand cmd) {
        return chatUserFriendGroupService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserFriendGroupAddCommand> cmds) {
        return chatUserFriendGroupService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatUserFriendGroupService.deleteByIds(ids);
    }

    /**
     * 获取用户好友分组信息
     *
     * @param userId
     * @return
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserFriendGroupDTO>> queryByUserId(@RequestParam String userId) {
        return chatUserFriendGroupService.queryByUserId(userId);
    }

}
