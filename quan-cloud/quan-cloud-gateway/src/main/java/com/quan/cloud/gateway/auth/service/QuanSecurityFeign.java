package com.quan.cloud.gateway.auth.service;

import com.quan.security.common.dto.request.AuthenticateRequest;
import com.quan.security.common.dto.response.AuthenticateResponse;
import com.quan.cloud.gateway.auth.service.impl.QuanSecurityFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangquan
 * @date 2020/3/11 16:24
 */
@FeignClient(name = "${quan.app.security.name}", url = "${quan.app.security.url:}", fallbackFactory = QuanSecurityFallback.class)
public interface QuanSecurityFeign {

    /**
     * 权限验证
     *
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/auth/validate")
    AuthenticateResponse validate(@RequestBody AuthenticateRequest req);
}
