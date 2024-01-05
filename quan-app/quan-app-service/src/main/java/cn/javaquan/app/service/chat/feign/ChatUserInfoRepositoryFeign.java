package cn.javaquan.app.service.chat.feign;

import cn.javaquan.app.service.chat.feign.fallback.ChatUserInfoRepositoryFallback;
import cn.javaquan.app.common.module.chat.ChatUserInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserInfoDTO;
import cn.javaquan.app.common.module.chat.ChatUserInfoQuery;
import cn.javaquan.app.common.module.chat.ChatUserInfoUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ChatUserInfoRepositoryFallback.class)
public interface ChatUserInfoRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/chat/user/info/page")
    Result<PageResult<ChatUserInfoDTO>> page(@SpringQueryMap ChatUserInfoQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/chat/user/info/details")
    Result<ChatUserInfoDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/chat/user/info/update")
    Result<Boolean> update(@RequestBody ChatUserInfoUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/chat/user/info/save")
    Result<Boolean> save(@RequestBody ChatUserInfoAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/chat/user/info/saveBatch")
    Result saveBatch(@RequestBody List<ChatUserInfoAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/chat/user/info/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/core/chat/user/info/queryByUserId")
    Result<ChatUserInfoDTO> queryByUserId(@RequestParam String userId);

    /**
     * 获取用户信息
     *
     * @param userIds
     * @return
     */
    @GetMapping("/core/chat/user/info/queryByUserIds")
    Result<List<ChatUserInfoDTO>> queryByUserIds(@RequestParam List<String> userIds);
}
