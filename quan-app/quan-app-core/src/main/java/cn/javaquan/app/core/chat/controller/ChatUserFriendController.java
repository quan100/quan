package cn.javaquan.app.core.chat.controller;

import cn.javaquan.app.core.chat.repository.ChatUserFriendRepository;
import cn.javaquan.app.common.module.chat.ChatUserFriendAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.chat.convert.ChatUserFriendAssembler;
import cn.javaquan.app.core.chat.entity.ChatUserFriendPO;
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
@RequestMapping("/core/chat/user/friend/")
public class ChatUserFriendController {

    private final ChatUserFriendRepository chatUserFriendRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserFriendPO>> page(ChatUserFriendQuery query) {
        ChatUserFriendPO po = ChatUserFriendAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatUserFriendRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatUserFriendPO> details(@RequestParam Long id) {
        return Result.success(chatUserFriendRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserFriendUpdateCommand cmd) {
        ChatUserFriendPO po = ChatUserFriendAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatUserFriendRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserFriendAddCommand cmd) {
        ChatUserFriendPO po = ChatUserFriendAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatUserFriendRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserFriendAddCommand> cmds) {
        List<ChatUserFriendPO> pos = ChatUserFriendAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatUserFriendRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatUserFriendRepository.removeByIds(ids));
    }

    /**
     * 获取用户好友id.
     * @param userId 用户id
     * @return 查询结果
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserFriendPO>> queryByUserId(@RequestParam String userId) {
        return Result.success(chatUserFriendRepository.queryByUserId(userId));
    }

}
