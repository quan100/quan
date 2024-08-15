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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 后台管理用户领域相关接口.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/manager/auth")
public class AuthController {

    private final SysUserLoginFeign sysUserLoginFeign;

    private final UserLoginService userLoginService;

    /**
     * 系统账号登录.
     * @param cmd 登录请求参数
     * @param request http servlet请求信息
     * @return 登录成功返回token
     */
    @ResponseBody
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid LoginCommand cmd, HttpServletRequest request) {
        cmd.setIp(request.getRemoteAddr());
        cmd.setSessionId(request.getSession().getId());
        return sysUserLoginFeign.accountLogin(cmd);
    }

    /**
     * 邮箱登录.
     * @param cmd 登录请求参数
     * @param request http servlet请求信息
     * @return 登录成功返回token
     */
    @ResponseBody
    @PostMapping("/emailLogin")
    public Result<String> emailLogin(@RequestBody @Valid EmailLoginCommand cmd, HttpServletRequest request) {
        cmd.setSessionId(request.getSession().getId());
        return sysUserLoginFeign.emailLogin(cmd);
    }

    /**
     * 退出登录.
     * @param token 登录成功返回的token
     * @return 操作结果
     */
    @ResponseBody
    @PostMapping("/logout")
    public Result loginOut(@AuthToken String token) {
        return sysUserLoginFeign.loginOut(token);
    }

    /**
     * 获取图片验证码.
     * @param request http servlet请求信息
     * @param response http servlet响应信息
     */
    @ResponseBody
    @GetMapping("/captcha")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        userLoginService.verifyCode(request, response);
    }

    /**
     * 获取邮箱验证码.
     * @param cmd 邮箱验证码请求参数
     * @param request http servlet请求信息
     * @return 邮箱验证码获取操作次数
     */
    @Limiter(params = "#cmd.email", message = "请求过于频繁，请稍后再试！", leaseTime = 60000, automaticReleaseLock = false)
    @ResponseBody
    @PostMapping("/email/captcha")
    public Result<Long> verifyEmailCode(@RequestBody EmailCodeCommand cmd, HttpServletRequest request) {
        cmd.setSessionId(request.getSession().getId());
        return sysUserLoginFeign.verifyEmailCode(cmd);
    }

}
