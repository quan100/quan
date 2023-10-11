package com.quan.cloud.gateway.auth.service.impl;

import com.quan.cloud.gateway.auth.service.QuanSecurityFeign;
import com.quan.security.common.dto.request.AuthenticateRequest;
import com.quan.security.common.dto.response.AuthenticateResponse;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author wangquan
 * @date 2020/3/11 18:33
 */
@Component
public class QuanSecurityFallback implements FallbackFactory<QuanSecurityFeign> {

    @Override
    public QuanSecurityFeign create(Throwable throwable) {
        throwable.printStackTrace();
        return new QuanSecurityFeign() {
            @Override
            public AuthenticateResponse validate(AuthenticateRequest req) {
                return null;
            }
        };
    }
}
