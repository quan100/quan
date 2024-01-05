package cn.javaquan.app.pm.bff.chat.feign;

import cn.javaquan.app.pm.bff.chat.feign.fallback.ChatUserFriendGroupServiceFallback;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
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
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ChatUserFriendGroupServiceFallback.class)
public interface ChatUserFriendGroupServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/chat/user/friend/group/page")
    Result<PageResult<ChatUserFriendGroupDTO>> page(@SpringQueryMap ChatUserFriendGroupQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/chat/user/friend/group/details")
    Result<ChatUserFriendGroupDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/chat/user/friend/group/update")
    Result<Boolean> update(@RequestBody ChatUserFriendGroupUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/chat/user/friend/group/save")
    Result<Boolean> save(@RequestBody ChatUserFriendGroupAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/chat/user/friend/group/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ChatUserFriendGroupAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/chat/user/friend/group/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
