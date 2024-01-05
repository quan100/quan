package cn.javaquan.app.mobile.bff.tools.controller;

import cn.javaquan.app.common.convert.PageResultAssembler;
import cn.javaquan.app.common.module.tools.OpenToolsQuery;
import cn.javaquan.app.common.module.tools.OpenToolsVO;
import cn.javaquan.app.common.module.tools.ToolsDTO;
import cn.javaquan.app.common.module.tools.ToolsQuery;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.mobile.bff.tools.convert.OpenToolsAssembler;
import cn.javaquan.app.mobile.bff.tools.feign.OpenToolsRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangquan
 * @version 1.0.0
 * @date 2020-02-12 19:50:38
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/tools/")
public class OpenToolsController {

    private final OpenToolsRepositoryFeign toolsRepositoryFeign;

    /**
     * 获取工具列表
     *
     * @return
     */
    @GetMapping(value = "page")
    public Result<PageResult<OpenToolsVO>> getList(OpenToolsQuery query) {
        ToolsQuery toolsQuery = OpenToolsAssembler.INSTANCE.toToolsQuery(query);
        toolsQuery.setListType(1);
        toolsQuery.setStatus(0);
        Result<PageResult<ToolsDTO>> result = toolsRepositoryFeign.page(toolsQuery);

        PageResult<OpenToolsVO> page = PageResultAssembler.INSTANCE.toPageResult(result.getData());
        page.setRecords(OpenToolsAssembler.INSTANCE.toOpenToolsVOList(result.getData().getRecords()));
        return Result.success(page);
    }

}
