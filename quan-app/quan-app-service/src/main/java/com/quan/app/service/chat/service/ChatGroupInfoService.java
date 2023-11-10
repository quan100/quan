package com.quan.app.service.chat.service;

import com.quan.app.common.module.chat.ChatGroupInfoAddCommand;
import com.quan.app.common.module.chat.ChatGroupInfoDTO;
import com.quan.app.common.module.chat.ChatGroupInfoQuery;
import com.quan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.chat.feign.ChatGroupInfoRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 群组信息
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ChatGroupInfoService {

    private final ChatGroupInfoRepositoryFeign chatGroupInfoRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ChatGroupInfoDTO>> page(ChatGroupInfoQuery query) {
        return chatGroupInfoRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ChatGroupInfoDTO> details(Long id) {
        return chatGroupInfoRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ChatGroupInfoUpdateCommand cmd) {
        return chatGroupInfoRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ChatGroupInfoAddCommand cmd) {
        return chatGroupInfoRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ChatGroupInfoAddCommand> cmds) {
        return chatGroupInfoRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return chatGroupInfoRepositoryFeign.deleteByIds(ids);
    }
}