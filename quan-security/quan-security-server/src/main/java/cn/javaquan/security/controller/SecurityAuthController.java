package cn.javaquan.security.controller;

import cn.javaquan.security.common.dto.entity.AuthEntity;
import cn.javaquan.security.common.dto.request.AuthenticateRequest;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;
import cn.javaquan.security.service.AuthenticateService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 权限认证接口.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RestController
@RequestMapping("/auth")
public class SecurityAuthController {

    @Resource
    private AuthenticateService authenticateService;

    /**
     * token验证.
     * <p>
     * 验证token是否合法，并返回验证成功保存的凭证信息<br>
     * 凭证信息为自定义任意信息，一般在登录成功之后，需要保存到凭证中的信息<br>
     * 该信息根据业务场景可自由搭配，在验证身份成功后，可获取该信息；以满足业务需求<br>
     * <br>
     * 认证类型type = 1 时，该身份为匿名验证，将返回临时token，用以满足匿名接口权限访问<br>
     * 认证类型type = 2 时，需携带身份验证（该身份为登录获取的token，也可以是匿名获取的token；若token为空，将返回临时token）<br>
     * 认证类型type = 3 时，需携带身份验证（该身份必须为登录获取的token）
     * @param request 认证信息
     * @return 验证结果
     */
    @PostMapping("/validate")
    public AuthenticateResponse validate(@RequestBody @Validated AuthenticateRequest request) {
        return authenticateService.authenticate(request);
    }

    /**
     * token获取.
     * <p>
     * 1、将生成认证token<br>
     * 2、保存的凭证信息，<b>建议不要放置敏感信息，如：密码等</b><br>
     * 3、返回认证token<br>
     * <br>
     * <b>*此接口建议仅在用户登录成功时调用；</b> 该接口配置白名单和用户权限； 仅登录成功或指定的IP可访问<br>
     * 在访问权限接口时，token是必须的
     * @param auth 认证信息
     * @return 认证token
     */
    @PostMapping("/token")
    public String token(@RequestBody @Validated AuthEntity auth) {
        return authenticateService.getToken(auth);
    }

    /**
     * 清除token信息.
     * <p>
     * 1、清除token<br>
     * 2、清除token凭证信息<br>
     * 该接口建议在做退出登录功能调用
     * @param token 认证token
     */
    @GetMapping("/clean")
    public void clean(@RequestParam String token) {
        authenticateService.cleanToken(token);
    }

}
