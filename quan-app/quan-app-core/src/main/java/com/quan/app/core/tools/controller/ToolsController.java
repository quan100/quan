package com.quan.app.core.tools.controller;

import com.quan.common.base.message.PageResult;
import com.quan.app.common.module.tools.ToolsAddCommand;
import com.quan.app.common.module.tools.ToolsQuery;
import com.quan.app.common.module.tools.ToolsUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.core.tools.convert.ToolsAssembler;
import com.quan.app.core.tools.entity.ToolsPO;
import com.quan.app.core.tools.repository.ToolsRepository;
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
@RequestMapping("/core/tools/")
public class ToolsController {

    private final ToolsRepository toolsRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ToolsQuery query) {
        ToolsPO po = ToolsAssembler.INSTANCE.toQueryPO(query);
        return Result.success(toolsRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(toolsRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ToolsUpdateCommand cmd) {
        ToolsPO po = ToolsAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(toolsRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ToolsAddCommand cmd) {
        ToolsPO po = ToolsAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(toolsRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ToolsAddCommand> cmds) {
        List<ToolsPO> pos = ToolsAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(toolsRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(toolsRepository.removeByIds(ids));
    }

    /**
     * 获取工具列表
     *
     * @param query
     * @return
     */
    @GetMapping("tools")
    public Result getTools(ToolsQuery query) {
        return Result.success(toolsRepository.getTools(query));
    }

}
