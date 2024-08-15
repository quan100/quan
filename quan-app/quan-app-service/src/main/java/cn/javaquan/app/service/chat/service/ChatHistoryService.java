package cn.javaquan.app.service.chat.service;

import cn.javaquan.app.service.chat.feign.ChatHistoryRepositoryFeign;
import cn.javaquan.app.common.module.chat.ChatHistoryAddCommand;
import cn.javaquan.app.common.module.chat.ChatHistoryDTO;
import cn.javaquan.app.common.module.chat.ChatHistoryQuery;
import cn.javaquan.app.common.module.chat.ChatHistoryUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 聊天记录表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatHistoryService {

    private final ChatHistoryRepositoryFeign chatHistoryRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ChatHistoryDTO>> page(ChatHistoryQuery query) {
        return chatHistoryRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<ChatHistoryDTO> details(Long id) {
        return chatHistoryRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ChatHistoryUpdateCommand cmd) {
        return chatHistoryRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ChatHistoryAddCommand cmd) {
        return chatHistoryRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<ChatHistoryAddCommand> cmds) {
        return chatHistoryRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatHistoryRepositoryFeign.deleteByIds(ids);
    }

}
