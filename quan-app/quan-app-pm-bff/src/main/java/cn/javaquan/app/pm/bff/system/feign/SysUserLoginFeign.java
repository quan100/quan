package cn.javaquan.app.pm.bff.system.feign;

import cn.javaquan.app.common.module.auth.CheckVerifyCodeEvent;
import cn.javaquan.app.common.module.auth.EmailCodeCommand;
import cn.javaquan.app.common.module.auth.EmailLoginCommand;
import cn.javaquan.app.common.module.auth.LoginCommand;
import cn.javaquan.app.common.module.auth.TripartiteLoginEvent;
import cn.javaquan.app.pm.bff.system.feign.fallback.SysUserLoginFallback;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户登录.
 *
 * @author wangquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = SysUserLoginFallback.class)
public interface SysUserLoginFeign {

    /**
     * 后台登录接口.
     * @param cmd 登录请求参数
     * @return 登录成功返回token
     */
    @PostMapping("/service/sys/user/login/accountLogin")
    Result<String> accountLogin(@RequestBody LoginCommand cmd);

    /**
     * 后台登录接口.
     * @param cmd 登录请求参数
     * @return 登录成功返回token
     */
    @PostMapping("/service/sys/user/login/emailLogin")
    Result<String> emailLogin(@RequestBody EmailLoginCommand cmd);

    /**
     * 其它第三方登录.
     * @param event 登录请求参数
     * @return 登录成功返回token
     */
    @PostMapping("/service/sys/user/login/tripartite")
    Result<String> tripartiteLogin(@RequestBody TripartiteLoginEvent event);

    /**
     * 退出登录接口.
     * @param token token
     * @return 操作是否成功
     */
    @PostMapping("/service/sys/user/login/logout")
    Result loginOut(@RequestBody String token);

    /**
     * 校验验证码.
     * @param event 校验邮箱验证码事件参数
     * @return 验证码校验是否成功
     */
    @PostMapping("/service/sys/user/login/verify/code/check")
    Result checkVerifyCode(@RequestBody CheckVerifyCodeEvent event);

    /**
     * 获取邮箱验证码.
     * @param cmd 邮箱验证码请求参数
     * @return 邮箱验证码获取操作次数
     */
    @PostMapping("/service/sys/user/login/verify/email/code")
    Result<Long> verifyEmailCode(@RequestBody EmailCodeCommand cmd);

}
