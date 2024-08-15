package cn.javaquan.app.service.chat.feign;

import cn.javaquan.app.service.chat.feign.fallback.ChatGroupInfoRepositoryFallback;
import cn.javaquan.app.common.module.chat.ChatGroupInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatGroupInfoDTO;
import cn.javaquan.app.common.module.chat.ChatGroupInfoQuery;
import cn.javaquan.app.common.module.chat.ChatGroupInfoUpdateCommand;
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
 * 群组信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = ChatGroupInfoRepositoryFallback.class)
public interface ChatGroupInfoRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/chat/group/info/page")
    Result<PageResult<ChatGroupInfoDTO>> page(@SpringQueryMap ChatGroupInfoQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/chat/group/info/details")
    Result<ChatGroupInfoDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/chat/group/info/update")
    Result<Boolean> update(@RequestBody ChatGroupInfoUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/chat/group/info/save")
    Result<Boolean> save(@RequestBody ChatGroupInfoAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/chat/group/info/saveBatch")
    Result saveBatch(@RequestBody List<ChatGroupInfoAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/chat/group/info/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
