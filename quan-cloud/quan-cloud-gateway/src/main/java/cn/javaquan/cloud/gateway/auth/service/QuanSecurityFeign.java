package cn.javaquan.cloud.gateway.auth.service;

import cn.javaquan.cloud.gateway.auth.service.impl.QuanSecurityFallback;
import cn.javaquan.security.common.dto.request.AuthenticateRequest;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 权限验证服务.
 *
 * @author wangquan
 * @since 1.0.0
 */
@FeignClient(name = "${quan.app.security.name}", url = "${quan.app.security.url:}",
        fallbackFactory = QuanSecurityFallback.class)
public interface QuanSecurityFeign {

    /**
     * 权限验证.
     * @param req 请求参数
     * @return 验证结果
     */
    @ResponseBody
    @PostMapping("/auth/validate")
    AuthenticateResponse validate(@RequestBody AuthenticateRequest req);

}
