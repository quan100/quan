package com.quan.app.pm.bff.system.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.auth.*;
import com.quan.app.pm.bff.system.feign.SysUserLoginFeign;
import com.quan.common.base.message.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SysUserLoginFallback implements FallbackFactory<SysUserLoginFeign> {
    @Override
    public SysUserLoginFeign create(Throwable throwable) {
        return new SysUserLoginFeign() {
            @Override
            public Result<String> accountLogin(LoginCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<String> emailLogin(EmailLoginCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<String> tripartiteLogin(TripartiteLoginEvent event) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result loginOut(String token) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result checkVerifyCode(CheckVerifyCodeEvent event) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<Long> verifyEmailCode(EmailCodeCommand cmd) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
