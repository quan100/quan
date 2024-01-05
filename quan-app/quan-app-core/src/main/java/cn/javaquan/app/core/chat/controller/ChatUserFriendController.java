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
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户好友信息关联表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/chat/user/friend/")
public class ChatUserFriendController {

    private final ChatUserFriendRepository chatUserFriendRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ChatUserFriendQuery query) {
        ChatUserFriendPO po = ChatUserFriendAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatUserFriendRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(chatUserFriendRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ChatUserFriendUpdateCommand cmd) {
        ChatUserFriendPO po = ChatUserFriendAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatUserFriendRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ChatUserFriendAddCommand cmd) {
        ChatUserFriendPO po = ChatUserFriendAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatUserFriendRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ChatUserFriendAddCommand> cmds) {
        List<ChatUserFriendPO> pos = ChatUserFriendAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatUserFriendRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatUserFriendRepository.removeByIds(ids));
    }

    /**
     * 获取用户好友id
     *
     * @param userId
     * @return
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserFriendPO>> queryByUserId(@RequestParam String userId) {
        return Result.success(chatUserFriendRepository.queryByUserId(userId));
    }

}
