package com.quan.app.core.chat.controller;

import com.quan.app.common.module.chat.ChatUserInfoAddCommand;
import com.quan.app.common.module.chat.ChatUserInfoQuery;
import com.quan.app.common.module.chat.ChatUserInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.core.chat.convert.ChatUserInfoAssembler;
import com.quan.app.core.chat.entity.ChatUserInfoPO;
import com.quan.app.core.chat.repository.ChatUserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户信息表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/chat/user/info/")
public class ChatUserInfoController {

    private final ChatUserInfoRepository chatUserInfoRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ChatUserInfoQuery query) {
        ChatUserInfoPO po = ChatUserInfoAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatUserInfoRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(chatUserInfoRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ChatUserInfoUpdateCommand cmd) {
        ChatUserInfoPO po = ChatUserInfoAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatUserInfoRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ChatUserInfoAddCommand cmd) {
        ChatUserInfoPO po = ChatUserInfoAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatUserInfoRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ChatUserInfoAddCommand> cmds) {
        List<ChatUserInfoPO> pos = ChatUserInfoAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatUserInfoRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatUserInfoRepository.removeByIds(ids));
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("queryByUserId")
    public Result<ChatUserInfoPO> queryByUserId(@RequestParam String userId) {
        return Result.success(chatUserInfoRepository.queryByUserId(userId));
    }

    /**
     * 获取用户信息
     *
     * @param userIds
     * @return
     */
    @GetMapping("queryByUserIds")
    public Result<List<ChatUserInfoPO>> queryByUserIds(@RequestParam List<String> userIds) {
        return Result.success(chatUserInfoRepository.queryByUserIds(userIds));
    }

}
