package cn.javaquan.app.service.chat.controller;

import cn.javaquan.app.common.module.chat.ChatUserFriendAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendDTO;
import cn.javaquan.app.common.module.chat.ChatUserFriendQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.service.ChatUserFriendService;
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
 * 用户好友信息关联表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/chat/user/friend/")
public class ChatUserFriendController {

    private final ChatUserFriendService chatUserFriendService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserFriendDTO>> page(ChatUserFriendQuery query) {
        return chatUserFriendService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatUserFriendDTO> details(@RequestParam Long id) {
        return chatUserFriendService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserFriendUpdateCommand cmd) {
        return chatUserFriendService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserFriendAddCommand cmd) {
        return chatUserFriendService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserFriendAddCommand> cmds) {
        return chatUserFriendService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatUserFriendService.deleteByIds(ids);
    }

    /**
     * 获取用户好友id.
     * @param userId 用户id
     * @return 用户好友信息
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserFriendDTO>> queryByUserId(@RequestParam String userId) {
        return chatUserFriendService.queryByUserId(userId);
    }

}
