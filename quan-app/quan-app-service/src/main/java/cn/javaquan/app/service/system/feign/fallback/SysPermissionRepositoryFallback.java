package cn.javaquan.app.service.system.feign.fallback;

import cn.javaquan.app.common.module.system.SubsetPermissionsQuery;
import cn.javaquan.app.common.module.system.SysPermissionAddCommand;
import cn.javaquan.app.common.module.system.SysPermissionDTO;
import cn.javaquan.app.common.module.system.SysPermissionQuery;
import cn.javaquan.app.common.module.system.SysPermissionUpdateCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.service.system.feign.SysPermissionRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.notify.SystemNotifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统资源权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class SysPermissionRepositoryFallback implements FallbackFactory<SysPermissionRepositoryFeign> {

    @Override
    public SysPermissionRepositoryFeign create(Throwable throwable) {
        return new SysPermissionRepositoryFeign() {
            @Override
            public Result page(SysPermissionQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(SysPermissionUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(SysPermissionAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<SysPermissionAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result getRolePermission(SysRolePermissionQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result getSubsetPermissions(SubsetPermissionsQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result getTreePermissions(SubsetPermissionsQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<List<SysPermissionDTO>> getPerms() {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
