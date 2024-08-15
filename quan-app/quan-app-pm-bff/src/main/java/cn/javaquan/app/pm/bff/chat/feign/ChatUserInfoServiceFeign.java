package cn.javaquan.app.pm.bff.chat.feign;

import cn.javaquan.app.pm.bff.chat.feign.fallback.ChatUserInfoServiceFallback;
import cn.javaquan.app.common.module.chat.ChatUserInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserInfoDTO;
import cn.javaquan.app.common.module.chat.ChatUserInfoQuery;
import cn.javaquan.app.common.module.chat.ChatUserInfoUpdateCommand;
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
 * 用户信息表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ChatUserInfoServiceFallback.class)
public interface ChatUserInfoServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/chat/user/info/page")
    Result<PageResult<ChatUserInfoDTO>> page(@SpringQueryMap ChatUserInfoQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/chat/user/info/details")
    Result<ChatUserInfoDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/chat/user/info/update")
    Result<Boolean> update(@RequestBody ChatUserInfoUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/chat/user/info/save")
    Result<Boolean> save(@RequestBody ChatUserInfoAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/chat/user/info/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ChatUserInfoAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/chat/user/info/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
