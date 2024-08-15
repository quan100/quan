package cn.javaquan.app.pm.bff.system.feign.fallback;

import cn.javaquan.app.common.exception.SystemException;
import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.system.AuthorizeRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRoleAddCommand;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.module.system.SysRoleUpdateCommand;
import cn.javaquan.app.pm.bff.system.feign.SysRoleServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class SysRoleServiceFallback implements FallbackFactory<SysRoleServiceFeign> {

    @Override
    public SysRoleServiceFeign create(Throwable throwable) {
        return new SysRoleServiceFeign() {
            @Override
            public Result page(SysRoleQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(SysRoleUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(SysRoleAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<SysRoleAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public void authorizeRolePermission(AuthorizeRolePermissionEvent event) {
                throw new SystemException(throwable.getMessage());
            }
        };
    }

}
