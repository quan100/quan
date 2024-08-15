package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.app.pm.bff.system.service.TripartiteLoginService;
import cn.javaquan.app.common.module.auth.BoundEvent;
import cn.javaquan.app.common.module.auth.TripartiteAuthEvent;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 钉钉登录.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RequestMapping("/dingtalk/login")
@RestController
public class DingtalkController {

    private final TripartiteLoginService tripartiteLoginService;

    /**
     * 获取用户token.
     * @param event 第三方账号登录事件参数
     * @return 登录成功返回token
     * @throws Exception 登录失败抛出异常
     */
    @GetMapping("/auth")
    public Result dingtalkAuth(@Valid TripartiteAuthEvent event) throws Exception {
        return tripartiteLoginService.dingtalkAuth(event);
    }

    /**
     * 授权绑定.
     * @param event 第三方账号登录授权绑定事件参数
     * @return 授权绑定操作是否成功
     */
    @PostMapping("/bound")
    public Result<Boolean> bound(@RequestBody BoundEvent event) {
        return tripartiteLoginService.bound(event);
    }

}
