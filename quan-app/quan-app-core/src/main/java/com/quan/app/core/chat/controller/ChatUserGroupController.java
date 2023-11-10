package com.quan.app.core.chat.controller;

import com.quan.app.common.module.chat.ChatUserGroupAddCommand;
import com.quan.app.common.module.chat.ChatUserGroupQuery;
import com.quan.app.common.module.chat.ChatUserGroupUpdateCommand;
import com.quan.app.core.chat.entity.ChatUserInfoPO;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.core.chat.convert.ChatUserGroupAssembler;
import com.quan.app.core.chat.entity.ChatUserGroupPO;
import com.quan.app.core.chat.repository.ChatUserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户群组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/chat/user/group/")
public class ChatUserGroupController {

    private final ChatUserGroupRepository chatUserGroupRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ChatUserGroupQuery query) {
        ChatUserGroupPO po = ChatUserGroupAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatUserGroupRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(chatUserGroupRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ChatUserGroupUpdateCommand cmd) {
        ChatUserGroupPO po = ChatUserGroupAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatUserGroupRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ChatUserGroupAddCommand cmd) {
        ChatUserGroupPO po = ChatUserGroupAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatUserGroupRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ChatUserGroupAddCommand> cmds) {
        List<ChatUserGroupPO> pos = ChatUserGroupAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatUserGroupRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatUserGroupRepository.removeByIds(ids));
    }

    /**
     * 根据用户ID查询用户加入的群
     *
     * @param userId
     * @return
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserGroupPO>> queryByUserId(@RequestParam String userId) {
        return Result.success(chatUserGroupRepository.queryByUserId(userId));
    }

}
