package cn.javaquan.app.mobile.bff.friendly.controller;

import cn.javaquan.app.common.module.friendly.FriendlyLinkApplyCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkVO;
import cn.javaquan.app.mobile.bff.friendly.service.OpenFriendlyService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.limiter.annotation.Limiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 友情链接相关接口.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/friendly/link/")
public class OpenFriendlyController {

    private final OpenFriendlyService openFriendlyService;

    /**
     * 查询列表.
     * @param basePage 分页查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<FriendlyLinkVO>> page(BasePage basePage) {
        return openFriendlyService.page(basePage);
    }

    /**
     * 申请友链.
     * <p>
     * 根据邮箱账号锁定申请操作频率，默认锁定5分钟
     * @param cmd 申请友链请求参数
     * @return 申请操作是否成功
     */
    @Limiter(params = "#cmd.email", leaseTime = 300000, automaticReleaseLock = false)
    @PostMapping("apply")
    public Result<Boolean> apply(@RequestBody @Valid FriendlyLinkApplyCommand cmd) {
        return openFriendlyService.apply(cmd);
    }

}
