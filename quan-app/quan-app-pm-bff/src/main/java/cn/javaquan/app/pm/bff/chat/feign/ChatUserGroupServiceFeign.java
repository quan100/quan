package cn.javaquan.app.pm.bff.chat.feign;

import cn.javaquan.app.pm.bff.chat.feign.fallback.ChatUserGroupServiceFallback;
import cn.javaquan.app.common.module.chat.ChatUserGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserGroupUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户群组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ChatUserGroupServiceFallback.class)
public interface ChatUserGroupServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/chat/user/group/page")
    Result<PageResult<ChatUserGroupDTO>> page(@SpringQueryMap ChatUserGroupQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/chat/user/group/details")
    Result<ChatUserGroupDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/chat/user/group/update")
    Result<Boolean> update(@RequestBody ChatUserGroupUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/chat/user/group/save")
    Result<Boolean> save(@RequestBody ChatUserGroupAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/chat/user/group/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ChatUserGroupAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/chat/user/group/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
