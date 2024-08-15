package cn.javaquan.app.service.chat.controller;

import cn.javaquan.app.common.module.chat.ChatUserGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserGroupUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.service.ChatUserGroupService;
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
 * 用户群组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/chat/user/group/")
public class ChatUserGroupController {

    private final ChatUserGroupService chatUserGroupService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserGroupDTO>> page(ChatUserGroupQuery query) {
        return chatUserGroupService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatUserGroupDTO> details(@RequestParam Long id) {
        return chatUserGroupService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserGroupUpdateCommand cmd) {
        return chatUserGroupService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserGroupAddCommand cmd) {
        return chatUserGroupService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserGroupAddCommand> cmds) {
        return chatUserGroupService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatUserGroupService.deleteByIds(ids);
    }

    /**
     * 根据用户ID查询用户加入的群.
     * @param userId 用户id
     * @return 用户加入的群
     */
    @GetMapping("queryByUserId")
    public Result<List<ChatUserGroupDTO>> queryByUserId(@RequestParam String userId) {
        return chatUserGroupService.queryByUserId(userId);
    }

}
