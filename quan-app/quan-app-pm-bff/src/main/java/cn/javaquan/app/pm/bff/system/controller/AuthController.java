package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.common.module.auth.EmailCodeCommand;
import cn.javaquan.app.common.module.auth.EmailLoginCommand;
import cn.javaquan.app.common.module.auth.LoginCommand;
import cn.javaquan.app.pm.bff.system.feign.SysUserLoginFeign;
import cn.javaquan.app.pm.bff.system.service.UserLoginService;
import cn.javaquan.security.common.annotation.AuthToken;
import cn.javaquan.tools.limiter.annotation.Limiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 后台管理用户领域相关接口
 *
 * @author wangquan
 * @date 2020/3/21 16:35
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/manager/auth")
public class AuthController {

    private final SysUserLoginFeign sysUserLoginFeign;
    private final UserLoginService userLoginService;

    /**
     * 系统账号登录
     *
     * @param cmd 登录请求参数
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public Result<String> login(@RequestBody @Valid LoginCommand cmd, HttpServletRequest request) {
        cmd.setIp(request.getRemoteAddr());
        cmd.setSessionId(request.getSession().getId());
        return sysUserLoginFeign.accountLogin(cmd);
    }

    /**
     * 邮箱登录
     *
     * @param cmd 登录请求参数
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/emailLogin")
    public Result<String> emailLogin(@RequestBody @Valid EmailLoginCommand cmd, HttpServletRequest request) {
        cmd.setSessionId(request.getSession().getId());
        return sysUserLoginFeign.emailLogin(cmd);
    }

    /**
     * 退出登录
     *
     * @param token
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/logout")
    public Result loginOut(@AuthToken String token) {
        return sysUserLoginFeign.loginOut(token);
    }

    /**
     * 获取图片验证码
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/captcha")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        userLoginService.verifyCode(request, response);
    }

    /**
     * 获取邮箱验证码
     *
     * @return
     */
    @Limiter(params = "#cmd.email", message = "请求过于频繁，请稍后再试！", leaseTime = 60000, automaticReleaseLock = false)
    @ResponseBody
    @PostMapping(value = "/email/captcha")
    public Result<Long> verifyEmailCode(@RequestBody EmailCodeCommand cmd, HttpServletRequest request) {
        cmd.setSessionId(request.getSession().getId());
        return sysUserLoginFeign.verifyEmailCode(cmd);
    }
}
