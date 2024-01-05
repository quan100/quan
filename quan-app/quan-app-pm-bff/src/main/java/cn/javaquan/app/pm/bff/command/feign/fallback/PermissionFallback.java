package cn.javaquan.app.pm.bff.command.feign.fallback;

import cn.javaquan.app.pm.bff.command.feign.PermissionFeign;
import cn.javaquan.tools.notice.SystemNoticeException;
import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 字典
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Component
public class PermissionFallback implements FallbackFactory<PermissionFeign> {

    @Override
    public PermissionFeign create(Throwable throwable) {
        return new PermissionFeign() {

            @Override
            public Result getUserPermission(AuthQuery query) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
