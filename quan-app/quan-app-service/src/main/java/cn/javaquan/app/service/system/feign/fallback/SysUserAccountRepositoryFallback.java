package cn.javaquan.app.service.system.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.app.service.system.feign.SysUserAccountRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户账号.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class SysUserAccountRepositoryFallback implements FallbackFactory<SysUserAccountRepositoryFeign> {

    @Override
    public SysUserAccountRepositoryFeign create(Throwable throwable) {
        return new SysUserAccountRepositoryFeign() {
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
            public Result getUserAccount(SysUserAccountQuery query) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
