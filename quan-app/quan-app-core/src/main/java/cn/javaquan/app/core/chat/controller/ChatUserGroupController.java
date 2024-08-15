package cn.javaquan.app.core.chat.controller;

import cn.javaquan.app.core.chat.repository.ChatUserGroupRepository;
import cn.javaquan.app.common.module.chat.ChatUserGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserGroupUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.chat.convert.ChatUserGroupAssembler;
import cn.javaquan.app.core.chat.entity.ChatUserGroupPO;
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
 * 用户群组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/chat/user/group/")
public class ChatUserGroupController {

    private final ChatUserGroupRepository chatUserGroupRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserGroupPO>> page(ChatUserGroupQuery query) {
        ChatUserGroupPO po = ChatUserGroupAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatUserGroupRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatUserGroupPO> details(@RequestParam Long id) {
        return Result.success(chatUserGroupRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserGroupUpdateCommand cmd) {
        ChatUserGroupPO po = ChatUserGroupAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatUserGroupRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserGroupAddCommand cmd) {
        ChatUserGroupPO po = ChatUserGroupAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatUserGroupRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserGroupAddCommand> cmds) {
        List<ChatUserGroupPO> pos = ChatUserGroupAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatUserGroupRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatUserGroupRepository.removeByIds(ids));
    }

    /**
     * 根据用户ID查询用户加入的群.
     * @param userId 用户ID
     * @return 查询结果
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserGroupPO>> queryByUserId(@RequestParam String userId) {
        return Result.success(chatUserGroupRepository.queryByUserId(userId));
    }

}
