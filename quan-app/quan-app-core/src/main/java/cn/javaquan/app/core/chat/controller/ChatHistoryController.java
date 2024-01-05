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
