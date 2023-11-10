package com.quan.app.service.chat.service;

import com.quan.app.common.module.chat.ChatHistoryAddCommand;
import com.quan.app.common.module.chat.ChatHistoryDTO;
import com.quan.app.common.module.chat.ChatHistoryQuery;
import com.quan.app.common.module.chat.ChatHistoryUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.chat.feign.ChatHistoryRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 聊天记录表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatHistoryService {

    private final ChatHistoryRepositoryFeign chatHistoryRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ChatHistoryDTO>> page(ChatHistoryQuery query) {
        return chatHistoryRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ChatHistoryDTO> details(Long id) {
        return chatHistoryRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ChatHistoryUpdateCommand cmd) {
        return chatHistoryRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ChatHistoryAddCommand cmd) {
        return chatHistoryRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ChatHistoryAddCommand> cmds) {
        return chatHistoryRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatHistoryRepositoryFeign.deleteByIds(ids);
    }
}