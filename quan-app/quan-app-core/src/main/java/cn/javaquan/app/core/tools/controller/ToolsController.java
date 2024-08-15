package cn.javaquan.app.core.tools.controller;

import cn.javaquan.app.core.tools.repository.ToolsRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.tools.ToolsAddCommand;
import cn.javaquan.app.common.module.tools.ToolsQuery;
import cn.javaquan.app.common.module.tools.ToolsUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.tools.convert.ToolsAssembler;
import cn.javaquan.app.core.tools.entity.ToolsPO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/tools/")
public class ToolsController {

    private final ToolsRepository toolsRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ToolsPO>> page(ToolsQuery query) {
        ToolsPO po = ToolsAssembler.INSTANCE.toQueryPO(query);
        return Result.success(toolsRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ToolsPO> details(@RequestParam Long id) {
        return Result.success(toolsRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ToolsUpdateCommand cmd) {
        ToolsPO po = ToolsAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(toolsRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ToolsAddCommand cmd) {
        ToolsPO po = ToolsAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(toolsRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ToolsAddCommand> cmds) {
        List<ToolsPO> pos = ToolsAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(toolsRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(toolsRepository.removeByIds(ids));
    }

    /**
     * 获取工具列表.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("tools")
    public Result<PageResult<ToolsPO>> getTools(ToolsQuery query) {
        return Result.success(toolsRepository.getTools(query));
    }

}
