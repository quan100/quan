package com.quan.app.pm.bff.chat.controller;

import com.quan.app.common.module.chat.ChatUserInfoAddCommand;
import com.quan.app.common.module.chat.ChatUserInfoDTO;
import com.quan.app.common.module.chat.ChatUserInfoQuery;
import com.quan.app.common.module.chat.ChatUserInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.chat.feign.ChatUserInfoServiceFeign;
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
@RequestMapping("/chat/user/info/")
public class ChatUserInfoController {

    private final ChatUserInfoServiceFeign chatUserInfoServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserInfoDTO>> page(ChatUserInfoQuery query) {
        return chatUserInfoServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ChatUserInfoDTO> details(@RequestParam Long id) {
        return chatUserInfoServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserInfoUpdateCommand cmd) {
        return chatUserInfoServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserInfoAddCommand cmd) {
        return chatUserInfoServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserInfoAddCommand> cmds) {
        return chatUserInfoServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatUserInfoServiceFeign.deleteByIds(ids);
    }

}
