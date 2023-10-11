package com.quan.app.service.tools.service;

import com.quan.app.common.module.tools.ToolsAddCommand;
import com.quan.app.common.module.tools.ToolsDTO;
import com.quan.app.common.module.tools.ToolsQuery;
import com.quan.app.common.module.tools.ToolsUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.tools.feign.ToolsRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 工具
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@Component
public class ToolsService {

    private final ToolsRepositoryFeign toolsRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ToolsDTO>> page(ToolsQuery query) {
        return toolsRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ToolsDTO> details(Long id) {
        return toolsRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ToolsUpdateCommand cmd) {
        return toolsRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ToolsAddCommand cmd) {
        return toolsRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ToolsAddCommand> cmds) {
        return toolsRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return toolsRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取工具列表
     *
     * @param query
     * @return
     */
    public Result getTools(ToolsQuery query) {
        return toolsRepositoryFeign.getTools(query);
    }
}