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
 * 工具
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = OpenToolsRepositoryFallback.class)
public interface OpenToolsRepositoryFeign {

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
     * 获取工具列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/tools/tools")
    Result getTools(@SpringQueryMap ToolsQuery query);
}
