package cn.javaquan.app.mobile.bff.friendly.controller;

import cn.javaquan.app.common.module.friendly.FriendlyLinkApplyCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkVO;
import cn.javaquan.app.mobile.bff.friendly.service.OpenFriendlyService;
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

    private final OpenFriendlyService openFriendlyService;

    /**
     * 查询列表
     *
     * @param basePage
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<FriendlyLinkVO>> page(BasePage basePage) {
        return openFriendlyService.page(basePage);
    }

    /**
     * 申请友链
     *
     * @param cmd
     * @return
     */
    @Limiter(params = "#cmd.email", leaseTime = 300000, automaticReleaseLock = false)
    @PostMapping("apply")
    public Result<Boolean> apply(@RequestBody @Valid FriendlyLinkApplyCommand cmd) {
        return openFriendlyService.apply(cmd);
    }

}
