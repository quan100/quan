package com.quan.app.pm.bff.chat.controller;

import com.quan.app.common.module.chat.ChatGroupInfoAddCommand;
import com.quan.app.common.module.chat.ChatGroupInfoDTO;
import com.quan.app.common.module.chat.ChatGroupInfoQuery;
import com.quan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.chat.feign.ChatGroupInfoServiceFeign;
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
@RequestMapping("/chat/group/info/")
public class ChatGroupInfoController {

    private final ChatGroupInfoServiceFeign chatGroupInfoServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ChatGroupInfoDTO>> page(ChatGroupInfoQuery query) {
        return chatGroupInfoServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ChatGroupInfoDTO> details(@RequestParam Long id) {
        return chatGroupInfoServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatGroupInfoUpdateCommand cmd) {
        return chatGroupInfoServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatGroupInfoAddCommand cmd) {
        return chatGroupInfoServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatGroupInfoAddCommand> cmds) {
        return chatGroupInfoServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatGroupInfoServiceFeign.deleteByIds(ids);
    }

}
