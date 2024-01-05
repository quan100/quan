package cn.javaquan.app.service.chat.feign;

import cn.javaquan.app.service.chat.feign.fallback.ChatUserFriendRepositoryFallback;
import cn.javaquan.app.common.module.chat.ChatUserFriendAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendDTO;
import cn.javaquan.app.common.module.chat.ChatUserFriendQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户好友信息关联表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ChatUserFriendRepositoryFallback.class)
public interface ChatUserFriendRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/chat/user/friend/page")
    Result<PageResult<ChatUserFriendDTO>> page(@SpringQueryMap ChatUserFriendQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/chat/user/friend/details")
    Result<ChatUserFriendDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/chat/user/friend/update")
    Result<Boolean> update(@RequestBody ChatUserFriendUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/chat/user/friend/save")
    Result<Boolean> save(@RequestBody ChatUserFriendAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/chat/user/friend/saveBatch")
    Result saveBatch(@RequestBody List<ChatUserFriendAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/chat/user/friend/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取用户好友id
     *
     * @param userId
     * @return
     */
    @GetMapping("/core/chat/user/friend/queryByUserId")
    Result<List<ChatUserFriendDTO>> queryByUserId(@RequestParam String userId);

}
