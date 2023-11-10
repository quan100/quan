package com.quan.app.service.chat.feign;

import com.quan.app.common.module.chat.ChatGroupInfoAddCommand;
import com.quan.app.common.module.chat.ChatGroupInfoDTO;
import com.quan.app.common.module.chat.ChatGroupInfoQuery;
import com.quan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.chat.feign.fallback.ChatGroupInfoRepositoryFallback;
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
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ChatGroupInfoRepositoryFallback.class)
public interface ChatGroupInfoRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/chat/group/info/page")
    Result<PageResult<ChatGroupInfoDTO>> page(@SpringQueryMap ChatGroupInfoQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/chat/group/info/details")
    Result<ChatGroupInfoDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/chat/group/info/update")
    Result<Boolean> update(@RequestBody ChatGroupInfoUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/chat/group/info/save")
    Result<Boolean> save(@RequestBody ChatGroupInfoAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/chat/group/info/saveBatch")
    Result saveBatch(@RequestBody List<ChatGroupInfoAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/chat/group/info/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
