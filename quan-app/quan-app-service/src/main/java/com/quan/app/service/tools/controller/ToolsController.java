package com.quan.app.service.tools.controller;

import com.quan.app.common.module.tools.ToolsAddCommand;
import com.quan.app.common.module.tools.ToolsDTO;
import com.quan.app.common.module.tools.ToolsQuery;
import com.quan.app.common.module.tools.ToolsUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.tools.service.ToolsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 工具
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/tools/")
public class ToolsController {

    private final ToolsService toolsService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ToolsDTO>> page(ToolsQuery query) {
        return toolsService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ToolsDTO> details(@RequestParam Long id) {
        return toolsService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ToolsUpdateCommand cmd) {
        return toolsService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ToolsAddCommand cmd) {
        return toolsService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ToolsAddCommand> cmds) {
        return toolsService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return toolsService.deleteByIds(ids);
    }

    /**
     * 获取工具列表
     *
     * @param query
     * @return
     */
    @GetMapping("tools")
    public Result getTools(ToolsQuery query) {
        return toolsService.getTools(query);
    }

}
