package cn.javaquan.app.core.chat.controller;

import cn.javaquan.app.core.chat.repository.ChatUserInfoRepository;
import cn.javaquan.app.common.module.chat.ChatUserInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserInfoQuery;
import cn.javaquan.app.common.module.chat.ChatUserInfoUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.chat.convert.ChatUserInfoAssembler;
import cn.javaquan.app.core.chat.entity.ChatUserInfoPO;
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
@RequestMapping("/core/chat/user/info/")
public class ChatUserInfoController {

    private final ChatUserInfoRepository chatUserInfoRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ChatUserInfoPO>> page(ChatUserInfoQuery query) {
        ChatUserInfoPO po = ChatUserInfoAssembler.INSTANCE.toQueryPO(query);
        return Result.success(chatUserInfoRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ChatUserInfoPO> details(@RequestParam Long id) {
        return Result.success(chatUserInfoRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ChatUserInfoUpdateCommand cmd) {
        ChatUserInfoPO po = ChatUserInfoAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(chatUserInfoRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ChatUserInfoAddCommand cmd) {
        ChatUserInfoPO po = ChatUserInfoAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(chatUserInfoRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ChatUserInfoAddCommand> cmds) {
        List<ChatUserInfoPO> pos = ChatUserInfoAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(chatUserInfoRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(chatUserInfoRepository.removeByIds(ids));
    }

    /**
     * 获取用户信息.
     * @param userId 用户ID
     * @return 查询结果
     */
    @GetMapping("queryByUserId")
    public Result<ChatUserInfoPO> queryByUserId(@RequestParam String userId) {
        return Result.success(chatUserInfoRepository.queryByUserId(userId));
    }

    /**
     * 获取用户信息.
     * @param userIds 用户ID
     * @return 查询结果
     */
    @GetMapping("queryByUserIds")
    public Result<List<ChatUserInfoPO>> queryByUserIds(@RequestParam List<String> userIds) {
        return Result.success(chatUserInfoRepository.queryByUserIds(userIds));
    }

}
