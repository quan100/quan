package cn.javaquan.app.core.chat.controller;

import cn.javaquan.app.core.chat.repository.ChatHistoryRepository;
import cn.javaquan.app.common.module.chat.ChatHistoryAddCommand;
import cn.javaquan.app.common.module.chat.ChatHistoryQuery;
import cn.javaquan.app.common.module.chat.ChatHistoryUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.chat.convert.ChatHistoryAssembler;
import cn.javaquan.app.core.chat.entity.ChatHistoryPO;
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
 * 聊天记录表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/chat/history/")
public class ChatHistoryController {

    private final ChatHistoryRepository chatHistoryRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatHistoryPO>> page(ChatHistoryQuery query) {
        ChatHistoryPO po = ChatHistoryAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatHistoryRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatHistoryPO> details(@RequestParam Long id) {
        return Result.success(chatHistoryRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatHistoryUpdateCommand cmd) {
        ChatHistoryPO po = ChatHistoryAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatHistoryRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatHistoryAddCommand cmd) {
        ChatHistoryPO po = ChatHistoryAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatHistoryRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatHistoryAddCommand> cmds) {
        List<ChatHistoryPO> pos = ChatHistoryAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatHistoryRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatHistoryRepository.removeByIds(ids));
    }

}
