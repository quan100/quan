package com.quan.app.service.chat.controller;

import com.quan.app.common.module.chat.ChatGroupInfoAddCommand;
import com.quan.app.common.module.chat.ChatGroupInfoDTO;
import com.quan.app.common.module.chat.ChatGroupInfoQuery;
import com.quan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.chat.service.ChatGroupInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 群组信息
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/chat/group/info/")
public class ChatGroupInfoController {

    private final ChatGroupInfoService chatGroupInfoService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ChatGroupInfoDTO>> page(ChatGroupInfoQuery query) {
        return chatGroupInfoService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ChatGroupInfoDTO> details(@RequestParam Long id) {
        return chatGroupInfoService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatGroupInfoUpdateCommand cmd) {
        return chatGroupInfoService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatGroupInfoAddCommand cmd) {
        return chatGroupInfoService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatGroupInfoAddCommand> cmds) {
        return chatGroupInfoService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatGroupInfoService.deleteByIds(ids);
    }

}
