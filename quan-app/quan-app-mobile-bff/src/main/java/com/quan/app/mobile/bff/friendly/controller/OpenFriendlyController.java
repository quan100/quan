package com.quan.app.mobile.bff.friendly.controller;

import com.quan.app.common.module.convert.PageResultAssembler;
import com.quan.app.common.module.friendly.FriendlyLinkDTO;
import com.quan.app.common.module.friendly.FriendlyLinkQuery;
import com.quan.app.common.module.friendly.FriendlyLinkVO;
import com.quan.app.common.util.RunUtil;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.friendly.convert.OpenFriendlyLinkAssembler;
import com.quan.app.mobile.bff.friendly.feign.OpenFriendlyLinkServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangquan
 * @version 1.0.0
 * @date 2020-02-12 19:50:38
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/friendly/link/")
public class OpenFriendlyController {

    private final OpenFriendlyLinkServiceFeign friendlyLinkServiceFeign;

    /**
     * 查询列表
     *
     * @param basePage
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<FriendlyLinkVO>> page(BasePage basePage) {
        FriendlyLinkQuery query = new FriendlyLinkQuery();
        query.setStatus(0);
        query.setPageNum(basePage.getPageNum());
        query.setPageSize(basePage.getPageSize());
        Result<PageResult<FriendlyLinkDTO>> result = friendlyLinkServiceFeign.page(query);
        return RunUtil.doRun(result, () -> {
            PageResult<FriendlyLinkVO> page = PageResultAssembler.INSTANCE.toPageResult(result.getData());
            page.setRecords(OpenFriendlyLinkAssembler.INSTANCE.toFriendlyLinkVoList(result.getData().getRecords()));
            return Result.success(page);
        });
    }

    /**
     * 获取列表详情
     *
     * @param id
     * @return
     */
    @GetMapping("queryById")
    public FriendlyLinkVO queryById(@RequestParam Long id) {
        Result<FriendlyLinkDTO> result = friendlyLinkServiceFeign.details(id);
        FriendlyLinkDTO friendlyLinkDTO = result.getData();
        if (friendlyLinkDTO.getStatus() == 0) {
            return null;
        }
        return OpenFriendlyLinkAssembler.INSTANCE.toFriendlyLinkVo(friendlyLinkDTO);
    }

}
