package cn.javaquan.app.service.chat.controller;

import cn.javaquan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.service.ChatUserFriendGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户好友分组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/chat/user/friend/group/")
public class ChatUserFriendGroupController {

    private final ChatUserFriendGroupService chatUserFriendGroupService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserFriendGroupDTO>> page(ChatUserFriendGroupQuery query) {
        return chatUserFriendGroupService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatUserFriendGroupDTO> details(@RequestParam Long id) {
        return chatUserFriendGroupService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserFriendGroupUpdateCommand cmd) {
        return chatUserFriendGroupService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserFriendGroupAddCommand cmd) {
        return chatUserFriendGroupService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserFriendGroupAddCommand> cmds) {
        return chatUserFriendGroupService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatUserFriendGroupService.deleteByIds(ids);
    }

    /**
     * 获取用户好友分组信息.
     * @param userId 用户id
     * @return 用户好友分组信息
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserFriendGroupDTO>> queryByUserId(@RequestParam String userId) {
        return chatUserFriendGroupService.queryByUserId(userId);
    }

}
