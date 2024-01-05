package cn.javaquan.app.service.chat.controller;

import cn.javaquan.app.common.module.chat.*;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.service.ChatUserInfoService;
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
@RequestMapping("/service/chat/user/info/")
public class ChatUserInfoController {

    private final ChatUserInfoService chatUserInfoService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserInfoDTO>> page(ChatUserInfoQuery query) {
        return chatUserInfoService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ChatUserInfoDTO> details(@RequestParam Long id) {
        return chatUserInfoService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserInfoUpdateCommand cmd) {
        return chatUserInfoService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserInfoAddCommand cmd) {
        return chatUserInfoService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserInfoAddCommand> cmds) {
        return chatUserInfoService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatUserInfoService.deleteByIds(ids);
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("queryByUserId")
    public Result<ChatUserInfoDTO> queryByUserId(@RequestParam String userId) {
        return chatUserInfoService.queryByUserId(userId);
    }

    /**
     * 获取用户信息
     *
     * @param userIds
     * @return
     */
    @GetMapping("queryByUserIds")
    public Result<List<ChatUserInfoDTO>> queryByUserIds(@RequestParam List<String> userIds) {
        return chatUserInfoService.queryByUserIds(userIds);
    }

}
