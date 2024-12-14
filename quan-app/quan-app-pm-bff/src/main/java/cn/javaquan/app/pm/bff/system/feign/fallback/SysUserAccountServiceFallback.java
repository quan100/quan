package cn.javaquan.app.pm.bff.system.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.app.pm.bff.system.feign.SysUserAccountServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户账号.
 *
 * @author javaquan
 * @since 2.3.2
 */
@Slf4j
@Component
public class SysUserAccountServiceFallback implements FallbackFactory<SysUserAccountServiceFeign> {

    @Override
    public SysUserAccountServiceFeign create(Throwable throwable) {
        return new SysUserAccountServiceFeign() {
            @Override
            public Result page(SysUserAccountQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(SysUserAccountUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(SysUserAccountAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<SysUserAccountAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result getUserPermission(AuthQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result getUserAccount(SysUserAccountQuery query) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
