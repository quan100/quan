package cn.javaquan.chat.bff.feign;

import cn.javaquan.chat.bff.feign.fallback.ChatUserFriendGroupServiceFallback;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户好友分组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ChatUserFriendGroupServiceFallback.class)
public interface ChatUserFriendGroupServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/chat/user/friend/group/page")
    Result<PageResult<ChatUserFriendGroupDTO>> page(@SpringQueryMap ChatUserFriendGroupQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/chat/user/friend/group/details")
    Result<ChatUserFriendGroupDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/chat/user/friend/group/update")
    Result<Boolean> update(@RequestBody ChatUserFriendGroupUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/chat/user/friend/group/save")
    Result<Boolean> save(@RequestBody ChatUserFriendGroupAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/chat/user/friend/group/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ChatUserFriendGroupAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/chat/user/friend/group/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取用户好友分组信息.
     * @param userId 用户id
     * @return 好友分组信息
     */
    @GetMapping("/service/chat/user/friend/group/queryByUserId")
    Result<List<ChatUserFriendGroupDTO>> queryByUserId(@RequestParam String userId);

}
