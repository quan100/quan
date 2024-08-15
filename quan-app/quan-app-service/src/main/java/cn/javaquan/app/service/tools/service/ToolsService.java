package cn.javaquan.app.service.tools.service;

import cn.javaquan.app.common.module.tools.ToolsAddCommand;
import cn.javaquan.app.common.module.tools.ToolsDTO;
import cn.javaquan.app.common.module.tools.ToolsQuery;
import cn.javaquan.app.common.module.tools.ToolsUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.tools.feign.ToolsRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ToolsService {

    private final ToolsRepositoryFeign toolsRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ToolsDTO>> page(ToolsQuery query) {
        return toolsRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<ToolsDTO> details(Long id) {
        return toolsRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ToolsUpdateCommand cmd) {
        return toolsRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ToolsAddCommand cmd) {
        return toolsRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<ToolsAddCommand> cmds) {
        return toolsRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return toolsRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 获取工具列表.
     * @param query 查询参数
     * @return 工具配置数据列表
     */
    public Result getTools(ToolsQuery query) {
        return toolsRepositoryFeign.getTools(query);
    }

}
