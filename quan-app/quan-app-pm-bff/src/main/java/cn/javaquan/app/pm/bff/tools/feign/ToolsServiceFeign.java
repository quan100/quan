package cn.javaquan.app.pm.bff.tools.feign;

import java.util.List;
import cn.javaquan.app.pm.bff.tools.feign.fallback.ToolsServiceFallback;
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

/**
 * 工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ToolsServiceFallback.class)
public interface ToolsServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/tools/page")
    Result<PageResult<ToolsDTO>> page(@SpringQueryMap ToolsQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/tools/details")
    Result<ToolsDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/tools/update")
    Result<Boolean> update(@RequestBody ToolsUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/tools/save")
    Result<Boolean> save(@RequestBody ToolsAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/tools/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ToolsAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/tools/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
