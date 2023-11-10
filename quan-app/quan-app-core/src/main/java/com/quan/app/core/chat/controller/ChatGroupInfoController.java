package com.quan.app.core.chat.controller;

import com.quan.app.common.module.chat.ChatGroupInfoAddCommand;
import com.quan.app.common.module.chat.ChatGroupInfoQuery;
import com.quan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.core.chat.convert.ChatGroupInfoAssembler;
import com.quan.app.core.chat.entity.ChatGroupInfoPO;
import com.quan.app.core.chat.repository.ChatGroupInfoRepository;
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
@RequestMapping("/core/chat/group/info/")
public class ChatGroupInfoController {

    private final ChatGroupInfoRepository chatGroupInfoRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ChatGroupInfoQuery query) {
        ChatGroupInfoPO po = ChatGroupInfoAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatGroupInfoRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(chatGroupInfoRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ChatGroupInfoUpdateCommand cmd) {
        ChatGroupInfoPO po = ChatGroupInfoAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatGroupInfoRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ChatGroupInfoAddCommand cmd) {
        ChatGroupInfoPO po = ChatGroupInfoAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatGroupInfoRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ChatGroupInfoAddCommand> cmds) {
        List<ChatGroupInfoPO> pos = ChatGroupInfoAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatGroupInfoRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatGroupInfoRepository.removeByIds(ids));
    }

}
