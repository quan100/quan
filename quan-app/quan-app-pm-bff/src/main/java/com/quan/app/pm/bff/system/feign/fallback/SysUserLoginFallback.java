package com.quan.app.pm.bff.system.feign.fallback;

import com.quan.app.common.module.auth.*;
import com.quan.app.common.module.auth.*;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.feign.SysUserLoginFeign;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SysUserLoginFallback implements FallbackFactory<SysUserLoginFeign> {
    @Override
    public SysUserLoginFeign create(Throwable throwable) {
        return new SysUserLoginFeign() {
            @Override
            public Result<String> accountLogin(LoginCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<String> emailLogin(EmailLoginCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<String> tripartiteLogin(TripartiteLoginEvent event) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result loginOut(String token) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result checkVerifyCode(CheckVerifyCodeEvent event) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<Long> verifyEmailCode(EmailCodeCommand cmd) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
