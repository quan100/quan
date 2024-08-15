package cn.javaquan.app.mobile.bff.tools.feign;

import cn.javaquan.app.mobile.bff.tools.feign.fallback.OpenToolsRepositoryFallback;
import cn.javaquan.app.common.module.tools.ToolsDTO;
import cn.javaquan.app.common.module.tools.ToolsQuery;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = OpenToolsRepositoryFallback.class)
public interface OpenToolsRepositoryFeign {

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
     * 获取工具列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/tools/tools")
    Result getTools(@SpringQueryMap ToolsQuery query);

}
