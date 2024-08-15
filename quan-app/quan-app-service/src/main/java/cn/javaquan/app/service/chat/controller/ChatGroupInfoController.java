package cn.javaquan.app.service.chat.controller;

import cn.javaquan.app.common.module.chat.ChatGroupInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatGroupInfoDTO;
import cn.javaquan.app.common.module.chat.ChatGroupInfoQuery;
import cn.javaquan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.service.ChatGroupInfoService;
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
 * 群组信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/chat/group/info/")
public class ChatGroupInfoController {

    private final ChatGroupInfoService chatGroupInfoService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatGroupInfoDTO>> page(ChatGroupInfoQuery query) {
        return chatGroupInfoService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatGroupInfoDTO> details(@RequestParam Long id) {
        return chatGroupInfoService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatGroupInfoUpdateCommand cmd) {
        return chatGroupInfoService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatGroupInfoAddCommand cmd) {
        return chatGroupInfoService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatGroupInfoAddCommand> cmds) {
        return chatGroupInfoService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return chatGroupInfoService.deleteByIds(ids);
    }

}
