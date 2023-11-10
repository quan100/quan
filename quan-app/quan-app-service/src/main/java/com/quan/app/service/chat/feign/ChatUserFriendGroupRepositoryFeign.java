package com.quan.app.service.chat.feign;

import com.quan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import com.quan.app.common.module.chat.ChatUserFriendGroupDTO;
import com.quan.app.common.module.chat.ChatUserFriendGroupQuery;
import com.quan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.chat.feign.fallback.ChatUserFriendGroupRepositoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户好友分组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ChatUserFriendGroupRepositoryFallback.class)
public interface ChatUserFriendGroupRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/chat/user/friend/group/page")
    Result<PageResult<ChatUserFriendGroupDTO>> page(@SpringQueryMap ChatUserFriendGroupQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/chat/user/friend/group/details")
    Result<ChatUserFriendGroupDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/chat/user/friend/group/update")
    Result<Boolean> update(@RequestBody ChatUserFriendGroupUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/chat/user/friend/group/save")
    Result<Boolean> save(@RequestBody ChatUserFriendGroupAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/chat/user/friend/group/saveBatch")
    Result saveBatch(@RequestBody List<ChatUserFriendGroupAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/chat/user/friend/group/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取用户好友分组信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/core/chat/user/friend/group/queryByUserId")
    Result<List<ChatUserFriendGroupDTO>> queryByUserId(@RequestParam String userId);
}
