package com.quan.app.service.tools.feign;

import com.quan.app.common.module.tools.ToolsAddCommand;
import com.quan.app.common.module.tools.ToolsDTO;
import com.quan.app.common.module.tools.ToolsQuery;
import com.quan.app.common.module.tools.ToolsUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.tools.feign.fallback.ToolsRepositoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工具
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ToolsRepositoryFallback.class)
public interface ToolsRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/tools/page")
    Result<PageResult<ToolsDTO>> page(@SpringQueryMap ToolsQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/tools/details")
    Result<ToolsDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/tools/update")
    Result<Boolean> update(@RequestBody ToolsUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/tools/save")
    Result<Boolean> save(@RequestBody ToolsAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/tools/saveBatch")
    Result saveBatch(@RequestBody List<ToolsAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/tools/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取工具列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/tools/tools")
    Result getTools(@SpringQueryMap ToolsQuery query);
}
