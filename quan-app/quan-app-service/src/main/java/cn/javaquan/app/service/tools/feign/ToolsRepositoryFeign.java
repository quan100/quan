package cn.javaquan.app.service.tools.feign;

import cn.javaquan.app.service.tools.feign.fallback.ToolsRepositoryFallback;
import cn.javaquan.app.common.module.tools.ToolsAddCommand;
import cn.javaquan.app.common.module.tools.ToolsDTO;
import cn.javaquan.app.common.module.tools.ToolsQuery;
import cn.javaquan.app.common.module.tools.ToolsUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = ToolsRepositoryFallback.class)
public interface ToolsRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/tools/page")
    Result<PageResult<ToolsDTO>> page(@SpringQueryMap ToolsQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/tools/details")
    Result<ToolsDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/tools/update")
    Result<Boolean> update(@RequestBody ToolsUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/tools/save")
    Result<Boolean> save(@RequestBody ToolsAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/tools/saveBatch")
    Result saveBatch(@RequestBody List<ToolsAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/tools/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取工具列表.
     * @param query 查询参数
     * @return 工具配置数据列表
     */
    @GetMapping("/core/tools/tools")
    Result getTools(@SpringQueryMap ToolsQuery query);

}
