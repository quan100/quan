package cn.javaquan.app.core.chat.controller;

import cn.javaquan.app.core.chat.repository.ChatGroupInfoRepository;
import cn.javaquan.app.common.module.chat.ChatGroupInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatGroupInfoQuery;
import cn.javaquan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.chat.convert.ChatGroupInfoAssembler;
import cn.javaquan.app.core.chat.entity.ChatGroupInfoPO;
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
@RequestMapping("/core/chat/group/info/")
public class ChatGroupInfoController {

    private final ChatGroupInfoRepository chatGroupInfoRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatGroupInfoPO>> page(ChatGroupInfoQuery query) {
        ChatGroupInfoPO po = ChatGroupInfoAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatGroupInfoRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatGroupInfoPO> details(@RequestParam Long id) {
        return Result.success(chatGroupInfoRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatGroupInfoUpdateCommand cmd) {
        ChatGroupInfoPO po = ChatGroupInfoAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatGroupInfoRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatGroupInfoAddCommand cmd) {
        ChatGroupInfoPO po = ChatGroupInfoAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatGroupInfoRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatGroupInfoAddCommand> cmds) {
        List<ChatGroupInfoPO> pos = ChatGroupInfoAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatGroupInfoRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatGroupInfoRepository.removeByIds(ids));
    }

}
