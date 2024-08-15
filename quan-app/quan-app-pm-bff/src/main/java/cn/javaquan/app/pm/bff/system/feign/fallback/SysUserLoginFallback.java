package cn.javaquan.app.pm.bff.system.feign.fallback;

import cn.javaquan.app.common.module.auth.CheckVerifyCodeEvent;
import cn.javaquan.app.common.module.auth.EmailCodeCommand;
import cn.javaquan.app.common.module.auth.EmailLoginCommand;
import cn.javaquan.app.common.module.auth.LoginCommand;
import cn.javaquan.app.common.module.auth.TripartiteLoginEvent;
import cn.javaquan.app.pm.bff.system.feign.SysUserLoginFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.notify.SystemNotifyException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户登录.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Component
public class SysUserLoginFallback implements FallbackFactory<SysUserLoginFeign> {

    @Override
    public SysUserLoginFeign create(Throwable throwable) {
        return new SysUserLoginFeign() {
            @Override
            public Result<String> accountLogin(LoginCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<String> emailLogin(EmailLoginCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<String> tripartiteLogin(TripartiteLoginEvent event) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result loginOut(String token) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result checkVerifyCode(CheckVerifyCodeEvent event) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<Long> verifyEmailCode(EmailCodeCommand cmd) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
