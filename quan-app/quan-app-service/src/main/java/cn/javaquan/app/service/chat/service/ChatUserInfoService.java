package cn.javaquan.app.service.chat.service;

import cn.javaquan.app.common.module.chat.ChatUserInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserInfoDTO;
import cn.javaquan.app.common.module.chat.ChatUserInfoQuery;
import cn.javaquan.app.common.module.chat.ChatUserInfoUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.feign.ChatUserInfoRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户信息表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatUserInfoService {

    private final ChatUserInfoRepositoryFeign chatUserInfoRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ChatUserInfoDTO>> page(ChatUserInfoQuery query) {
        return chatUserInfoRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<ChatUserInfoDTO> details(Long id) {
        return chatUserInfoRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ChatUserInfoUpdateCommand cmd) {
        return chatUserInfoRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ChatUserInfoAddCommand cmd) {
        return chatUserInfoRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<ChatUserInfoAddCommand> cmds) {
        return chatUserInfoRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatUserInfoRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取用户信息.
     * @param userId 用户id
     * @return 用户信息
     */
    public Result<ChatUserInfoDTO> queryByUserId(String userId) {
        return chatUserInfoRepositoryFeign.queryByUserId(userId);
    }

    /**
     * 获取用户信息.
     * @param userIds 用户id
     * @return 用户信息
     */
    public Result<List<ChatUserInfoDTO>> queryByUserIds(List<String> userIds) {
        return chatUserInfoRepositoryFeign.queryByUserIds(userIds);
    }

}
