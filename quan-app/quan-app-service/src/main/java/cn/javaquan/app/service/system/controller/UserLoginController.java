package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.auth.*;
import cn.javaquan.app.service.system.service.UserLoginService;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户登录
 *
 * @author wangquan
 * @version 1.0.0
 * @date 2020-08-24 23:47:27
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/user/login")
public class UserLoginController {

    private final UserLoginService userService;

    /**
     * 账号登录
     *
     * @param cmd 登录请求参数
     * @return
     */
    @PostMapping(value = "/accountLogin")
    public Result<String> accountLogin(@RequestBody @Valid LoginCommand cmd) {
        return userService.accountLogin(cmd);
    }

    /**
     * 邮箱登录
     *
     * @param cmd 登录请求参数
     * @return
     */
    @PostMapping(value = "/emailLogin")
    public Result<String> emailLogin(@RequestBody @Valid EmailLoginCommand cmd) {
        return userService.emailLogin(cmd);
    }

    /**
     * 其它第三方登录
     *
     * @param event 登录请求参数
     * @return
     */
    @PostMapping(value = "/tripartite")
    public Result<String> tripartiteLogin(@RequestBody @Valid TripartiteLoginEvent event) {
        return userService.tripartiteLogin(event);
    }

    /**
     * 退出登录接口
     *
     * @param token
     * @return
     */
    @PostMapping(value = "/logout")
    public Result loginOut(@RequestBody String token) {
        return userService.loginOut(token);
    }

    /**
     * 校验验证码
     *
     * @param event
     * @return
     */
    @PostMapping(value = "/verify/code/check")
    public Result verifyImageCode(@RequestBody @Valid CheckVerifyCodeEvent event) {
        return userService.verifyImageCode(event.getVerifyCode(), event.getSessionId());
    }

//    /**
    // TODO: 2020/4/12 待处理
//     * 初始化菜单
//     *
//     * @param cmd 登录请求参数
//     * @return
//     */
//    @ApiOperation(value = "初始化菜单", notes = "初始化菜单")
//    @PostMapping(value = "/initMenu")
//    public Message initMenu(@RequestBody @Valid LoginRequest cmd) {
//        return userFeign.initMenu(cmd);
//    }

    /**
     * 获取邮箱验证码
     *
     * @return
     */
    @PostMapping(value = "/verify/email/code")
    public Result<Long> verifyEmailCode(@RequestBody EmailCodeCommand cmd) {
        return userService.verifyAndCreateEmailCode(cmd.getEmail(), cmd.getCaptcha(), cmd.getSessionId());
    }

}

