package com.quan.app.pm.bff.chat.controller;

import com.quan.app.common.module.chat.ChatHistoryAddCommand;
import com.quan.app.common.module.chat.ChatHistoryDTO;
import com.quan.app.common.module.chat.ChatHistoryQuery;
import com.quan.app.common.module.chat.ChatHistoryUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.chat.feign.ChatHistoryServiceFeign;
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
@RequestMapping("/chat/history/")
public class ChatHistoryController {

    private final ChatHistoryServiceFeign chatHistoryServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ChatHistoryDTO>> page(ChatHistoryQuery query) {
        return chatHistoryServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ChatHistoryDTO> details(@RequestParam Long id) {
        return chatHistoryServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatHistoryUpdateCommand cmd) {
        return chatHistoryServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatHistoryAddCommand cmd) {
        return chatHistoryServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatHistoryAddCommand> cmds) {
        return chatHistoryServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatHistoryServiceFeign.deleteByIds(ids);
    }

}
