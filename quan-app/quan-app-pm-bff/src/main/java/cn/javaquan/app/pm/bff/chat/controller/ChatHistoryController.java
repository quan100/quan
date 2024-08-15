package cn.javaquan.app.pm.bff.chat.controller;

import cn.javaquan.app.common.module.chat.ChatHistoryAddCommand;
import cn.javaquan.app.common.module.chat.ChatHistoryDTO;
import cn.javaquan.app.common.module.chat.ChatHistoryQuery;
import cn.javaquan.app.common.module.chat.ChatHistoryUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.pm.bff.chat.feign.ChatHistoryServiceFeign;
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
@RequestMapping("/chat/history/")
public class ChatHistoryController {

    private final ChatHistoryServiceFeign chatHistoryServiceFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatHistoryDTO>> page(ChatHistoryQuery query) {
        return chatHistoryServiceFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatHistoryDTO> details(@RequestParam Long id) {
        return chatHistoryServiceFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatHistoryUpdateCommand cmd) {
        return chatHistoryServiceFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatHistoryAddCommand cmd) {
        return chatHistoryServiceFeign.save(cmd);
    }

    /**
     * 新增.
     * @param cmds 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatHistoryAddCommand> cmds) {
        return chatHistoryServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatHistoryServiceFeign.deleteByIds(ids);
    }

}
