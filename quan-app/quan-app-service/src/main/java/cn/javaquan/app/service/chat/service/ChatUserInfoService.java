package cn.javaquan.app.service.chat.service;

import cn.javaquan.app.common.module.chat.*;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.chat.feign.ChatUserInfoRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户信息表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatUserInfoService {

    private final ChatUserInfoRepositoryFeign chatUserInfoRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ChatUserInfoDTO>> page(ChatUserInfoQuery query) {
        return chatUserInfoRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ChatUserInfoDTO> details(Long id) {
        return chatUserInfoRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ChatUserInfoUpdateCommand cmd) {
        return chatUserInfoRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ChatUserInfoAddCommand cmd) {
        return chatUserInfoRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ChatUserInfoAddCommand> cmds) {
        return chatUserInfoRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatUserInfoRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    public Result<ChatUserInfoDTO> queryByUserId(String userId) {
        return chatUserInfoRepositoryFeign.queryByUserId(userId);
    }

    /**
     * 获取用户信息
     *
     * @param userIds
     * @return
     */
    public Result<List<ChatUserInfoDTO>> queryByUserIds(List<String> userIds) {
        return chatUserInfoRepositoryFeign.queryByUserIds(userIds);
    }
}
