package com.quan.app.core.chat.controller;

import com.quan.app.common.module.chat.ChatHistoryAddCommand;
import com.quan.app.common.module.chat.ChatHistoryQuery;
import com.quan.app.common.module.chat.ChatHistoryUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.core.chat.convert.ChatHistoryAssembler;
import com.quan.app.core.chat.entity.ChatHistoryPO;
import com.quan.app.core.chat.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 聊天记录表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/chat/history/")
public class ChatHistoryController {

    private final ChatHistoryRepository chatHistoryRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ChatHistoryQuery query) {
        ChatHistoryPO po = ChatHistoryAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatHistoryRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(chatHistoryRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ChatHistoryUpdateCommand cmd) {
        ChatHistoryPO po = ChatHistoryAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatHistoryRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ChatHistoryAddCommand cmd) {
        ChatHistoryPO po = ChatHistoryAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatHistoryRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ChatHistoryAddCommand> cmds) {
        List<ChatHistoryPO> pos = ChatHistoryAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatHistoryRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatHistoryRepository.removeByIds(ids));
    }

}
