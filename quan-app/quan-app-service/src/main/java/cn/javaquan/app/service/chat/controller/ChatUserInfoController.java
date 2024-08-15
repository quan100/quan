package cn.javaquan.app.service.chat.controller;

import cn.javaquan.app.common.module.chat.ChatUserInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserInfoDTO;
import cn.javaquan.app.common.module.chat.ChatUserInfoQuery;
import cn.javaquan.app.common.module.chat.ChatUserInfoUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.service.ChatUserInfoService;
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
 * 用户信息表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/chat/user/info/")
public class ChatUserInfoController {

    private final ChatUserInfoService chatUserInfoService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserInfoDTO>> page(ChatUserInfoQuery query) {
        return chatUserInfoService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatUserInfoDTO> details(@RequestParam Long id) {
        return chatUserInfoService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserInfoUpdateCommand cmd) {
        return chatUserInfoService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserInfoAddCommand cmd) {
        return chatUserInfoService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserInfoAddCommand> cmds) {
        return chatUserInfoService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatUserInfoService.deleteByIds(ids);
    }

    /**
     * 获取用户信息.
     * @param userId 用户id
     * @return 用户信息
     */
    @GetMapping("queryByUserId")
    public Result<ChatUserInfoDTO> queryByUserId(@RequestParam String userId) {
        return chatUserInfoService.queryByUserId(userId);
    }

    /**
     * 获取用户信息.
     * @param userIds 用户id
     * @return 用户信息
     */
    @GetMapping("queryByUserIds")
    public Result<List<ChatUserInfoDTO>> queryByUserIds(@RequestParam List<String> userIds) {
        return chatUserInfoService.queryByUserIds(userIds);
    }

}
