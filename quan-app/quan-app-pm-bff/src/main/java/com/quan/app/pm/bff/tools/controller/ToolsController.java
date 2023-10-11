package com.quan.app.pm.bff.tools.controller;

import com.quan.app.common.module.tools.ToolsAddCommand;
import com.quan.app.common.module.tools.ToolsDTO;
import com.quan.app.common.module.tools.ToolsQuery;
import com.quan.app.common.module.tools.ToolsUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.tools.feign.ToolsServiceFeign;
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
@RequestMapping("/tools/")
public class ToolsController {

    private final ToolsServiceFeign toolsServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ToolsDTO>> page(ToolsQuery query) {
        return toolsServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ToolsDTO> details(@RequestParam Long id) {
        return toolsServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ToolsUpdateCommand cmd) {
        return toolsServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ToolsAddCommand cmd) {
        return toolsServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ToolsAddCommand> cmds) {
        return toolsServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return toolsServiceFeign.deleteByIds(ids);
    }

}
