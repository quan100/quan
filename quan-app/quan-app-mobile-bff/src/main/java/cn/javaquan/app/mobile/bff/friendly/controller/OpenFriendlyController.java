package cn.javaquan.app.mobile.bff.friendly.controller;

import cn.javaquan.app.common.convert.PageResultAssembler;
import cn.javaquan.app.common.module.friendly.*;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.mobile.bff.friendly.convert.OpenFriendlyLinkAssembler;
import cn.javaquan.app.mobile.bff.friendly.feign.OpenFriendlyLinkServiceFeign;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.limiter.annotation.Limiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
     * 申请友链
     *
     * @param cmd
     * @return
     */
    @Limiter(params = "#cmd.email", leaseTime = 300000, automaticReleaseLock = false)
    @PostMapping("apply")
    public Result<Boolean> save(@RequestBody @Valid FriendlyLinkApplyCommand cmd) {
        FriendlyLinkAddCommand addCommand = OpenFriendlyLinkAssembler.INSTANCE.toFriendlyLinkAddCommand(cmd);
        return friendlyLinkServiceFeign.save(addCommand);
    }

}
