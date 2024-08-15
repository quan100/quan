package cn.javaquan.app.pm.bff.chat.feign;

import cn.javaquan.app.pm.bff.chat.feign.fallback.ChatHistoryServiceFallback;
import cn.javaquan.app.common.module.chat.ChatHistoryAddCommand;
import cn.javaquan.app.common.module.chat.ChatHistoryDTO;
import cn.javaquan.app.common.module.chat.ChatHistoryQuery;
import cn.javaquan.app.common.module.chat.ChatHistoryUpdateCommand;
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
 * 聊天记录表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ChatHistoryServiceFallback.class)
public interface ChatHistoryServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/chat/history/page")
    Result<PageResult<ChatHistoryDTO>> page(@SpringQueryMap ChatHistoryQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/chat/history/details")
    Result<ChatHistoryDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/chat/history/update")
    Result<Boolean> update(@RequestBody ChatHistoryUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/chat/history/save")
    Result<Boolean> save(@RequestBody ChatHistoryAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/chat/history/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ChatHistoryAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/chat/history/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
