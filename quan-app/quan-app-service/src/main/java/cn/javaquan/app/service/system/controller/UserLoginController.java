package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.auth.CheckVerifyCodeEvent;
import cn.javaquan.app.common.module.auth.EmailCodeCommand;
import cn.javaquan.app.common.module.auth.EmailLoginCommand;
import cn.javaquan.app.common.module.auth.LoginCommand;
import cn.javaquan.app.common.module.auth.TripartiteLoginEvent;
import cn.javaquan.app.service.system.service.UserLoginService;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户登录.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/user/login")
public class UserLoginController {

    private final UserLoginService userService;

    /**
     * 账号登录.
     * @param cmd 登录请求参数
     * @return 登录成功返回token
     */
    @PostMapping("/accountLogin")
    public Result<String> accountLogin(@RequestBody @Valid LoginCommand cmd) {
        return userService.accountLogin(cmd);
    }

    /**
     * 邮箱登录.
     * @param cmd 登录请求参数
     * @return 登录成功返回token
     */
    @PostMapping("/emailLogin")
    public Result<String> emailLogin(@RequestBody @Valid EmailLoginCommand cmd) {
        return userService.emailLogin(cmd);
    }

    /**
     * 其它第三方登录.
     * @param event 登录请求参数
     * @return 登录成功返回token
     */
    @PostMapping("/tripartite")
    public Result<String> tripartiteLogin(@RequestBody @Valid TripartiteLoginEvent event) {
        return userService.tripartiteLogin(event);
    }

    /**
     * 退出登录接口.
     * @param token token
     * @return 操作是否成功
     */
    @PostMapping("/logout")
    public Result loginOut(@RequestBody String token) {
        return userService.loginOut(token);
    }

    /**
     * 校验验证码.
     * @param event 校验邮箱验证码事件参数
     * @return 验证码校验是否成功
     */
    @PostMapping("/verify/code/check")
    public Result verifyImageCode(@RequestBody @Valid CheckVerifyCodeEvent event) {
        return userService.verifyImageCode(event.getVerifyCode(), event.getSessionId());
    }

    // /**
    // TODO: 2020/4/12 待处理
    // * 初始化菜单.
    // *
    // * @param cmd 登录请求参数
    // * @return
    // */
    // @ApiOperation(value = "初始化菜单", notes = "初始化菜单")
    // @PostMapping("/initMenu")
    // public Message initMenu(@RequestBody @Valid LoginRequest cmd) {
    // return userFeign.initMenu(cmd);
    // }

    /**
     * 获取邮箱验证码.
     * @param cmd 邮箱验证码请求参数
     * @return 邮箱验证码获取操作次数
     */
    @PostMapping("/verify/email/code")
    public Result<Long> verifyEmailCode(@RequestBody EmailCodeCommand cmd) {
        return userService.verifyAndCreateEmailCode(cmd.getEmail(), cmd.getCaptcha(), cmd.getSessionId());
    }

}
