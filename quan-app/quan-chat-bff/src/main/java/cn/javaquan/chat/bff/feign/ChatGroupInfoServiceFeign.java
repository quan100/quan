package cn.javaquan.chat.bff.feign;

import cn.javaquan.chat.bff.feign.fallback.ChatGroupInfoServiceFallback;
import com.quan.app.common.module.chat.ChatGroupInfoAddCommand;
import com.quan.app.common.module.chat.ChatGroupInfoDTO;
import com.quan.app.common.module.chat.ChatGroupInfoQuery;
import com.quan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 群组信息
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ChatGroupInfoServiceFallback.class)
public interface ChatGroupInfoServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/chat/group/info/page")
    Result<PageResult<ChatGroupInfoDTO>> page(@SpringQueryMap ChatGroupInfoQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/chat/group/info/details")
    Result<ChatGroupInfoDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/chat/group/info/update")
    Result<Boolean> update(@RequestBody ChatGroupInfoUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/chat/group/info/save")
    Result<Boolean> save(@RequestBody ChatGroupInfoAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/chat/group/info/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ChatGroupInfoAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/chat/group/info/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
