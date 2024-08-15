package cn.javaquan.app.pm.bff.command.feign.fallback;

import cn.javaquan.app.pm.bff.command.feign.PermissionFeign;
import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 字典.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Component
public class PermissionFallback implements FallbackFactory<PermissionFeign> {

    @Override
    public PermissionFeign create(Throwable throwable) {
        return new PermissionFeign() {

            @Override
            public Result getUserPermission(AuthQuery query) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
