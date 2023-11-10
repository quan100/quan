package cn.javaquan.chat.bff.feign;

import cn.javaquan.chat.bff.feign.fallback.ChatHistoryServiceFallback;
import com.quan.app.common.module.chat.ChatHistoryAddCommand;
import com.quan.app.common.module.chat.ChatHistoryDTO;
import com.quan.app.common.module.chat.ChatHistoryQuery;
import com.quan.app.common.module.chat.ChatHistoryUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 聊天记录表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ChatHistoryServiceFallback.class)
public interface ChatHistoryServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/chat/history/page")
    Result<PageResult<ChatHistoryDTO>> page(@SpringQueryMap ChatHistoryQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/chat/history/details")
    Result<ChatHistoryDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/chat/history/update")
    Result<Boolean> update(@RequestBody ChatHistoryUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/chat/history/save")
    Result<Boolean> save(@RequestBody ChatHistoryAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/chat/history/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ChatHistoryAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/chat/history/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
