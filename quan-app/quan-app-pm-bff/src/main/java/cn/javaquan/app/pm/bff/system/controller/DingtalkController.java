package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.app.pm.bff.system.service.TripartiteLoginService;
import cn.javaquan.app.common.module.auth.BoundEvent;
import cn.javaquan.app.common.module.auth.TripartiteAuthEvent;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 钉钉登录
 *
 * @author wangquan
 */
@RequiredArgsConstructor
@RequestMapping("/dingtalk/login")
@RestController
public class DingtalkController {

    private final TripartiteLoginService tripartiteLoginService;

    /**
     * 获取用户token
     *
     * @param event
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/auth")
    public Result dingtalkAuth(@Valid TripartiteAuthEvent event) throws Exception {
        return tripartiteLoginService.dingtalkAuth(event);
    }

    /**
     * 授权绑定
     *
     * @param event
     * @return
     */
    @PostMapping(value = "/bound")
    public Result<String> bound(@RequestBody BoundEvent event) {
        return tripartiteLoginService.bound(event);
    }
}
