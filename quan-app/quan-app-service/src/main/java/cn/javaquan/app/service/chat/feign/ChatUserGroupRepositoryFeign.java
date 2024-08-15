package cn.javaquan.app.service.chat.feign;

import cn.javaquan.app.service.chat.feign.fallback.ChatUserGroupRepositoryFallback;
import cn.javaquan.app.common.module.chat.ChatUserGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserGroupUpdateCommand;
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
 * 用户群组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = ChatUserGroupRepositoryFallback.class)
public interface ChatUserGroupRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/chat/user/group/page")
    Result<PageResult<ChatUserGroupDTO>> page(@SpringQueryMap ChatUserGroupQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/chat/user/group/details")
    Result<ChatUserGroupDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/chat/user/group/update")
    Result<Boolean> update(@RequestBody ChatUserGroupUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/chat/user/group/save")
    Result<Boolean> save(@RequestBody ChatUserGroupAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/chat/user/group/saveBatch")
    Result saveBatch(@RequestBody List<ChatUserGroupAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/chat/user/group/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据用户ID查询用户加入的群.
     * @param userId 用户id
     * @return 用户加入的群
     */
    @GetMapping("/core/chat/user/group/queryByUserId")
    Result<List<ChatUserGroupDTO>> queryByUserId(@RequestParam String userId);

}
