package cn.javaquan.app.core.chat.controller;

import cn.javaquan.app.core.chat.repository.ChatUserFriendGroupRepository;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.chat.convert.ChatUserFriendGroupAssembler;
import cn.javaquan.app.core.chat.entity.ChatUserFriendGroupPO;
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
@RequestMapping("/core/chat/user/friend/group/")
public class ChatUserFriendGroupController {

    private final ChatUserFriendGroupRepository chatUserFriendGroupRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserFriendGroupPO>> page(ChatUserFriendGroupQuery query) {
        ChatUserFriendGroupPO po = ChatUserFriendGroupAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatUserFriendGroupRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatUserFriendGroupPO> details(@RequestParam Long id) {
        return Result.success(chatUserFriendGroupRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserFriendGroupUpdateCommand cmd) {
        ChatUserFriendGroupPO po = ChatUserFriendGroupAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatUserFriendGroupRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserFriendGroupAddCommand cmd) {
        ChatUserFriendGroupPO po = ChatUserFriendGroupAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatUserFriendGroupRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserFriendGroupAddCommand> cmds) {
        List<ChatUserFriendGroupPO> pos = ChatUserFriendGroupAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatUserFriendGroupRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatUserFriendGroupRepository.removeByIds(ids));
    }

    /**
     * 获取用户好友分组信息.
     * @param userId 用户ID
     * @return 查询结果
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserFriendGroupPO>> queryByUserId(@RequestParam String userId) {
        return Result.success(chatUserFriendGroupRepository.queryByUserId(userId));
    }

}
