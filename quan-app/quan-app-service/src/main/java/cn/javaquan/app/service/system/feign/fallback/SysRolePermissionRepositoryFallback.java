package cn.javaquan.app.service.system.feign.fallback;

import cn.javaquan.app.common.module.system.PermissionRoleDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionAddCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.module.system.SysRolePermissionUpdateCommand;
import cn.javaquan.app.service.system.feign.SysRolePermissionRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.notify.SystemNotifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class SysRolePermissionRepositoryFallback implements FallbackFactory<SysRolePermissionRepositoryFeign> {

    @Override
    public SysRolePermissionRepositoryFeign create(Throwable throwable) {
        return new SysRolePermissionRepositoryFeign() {
            @Override
            public Result page(SysRolePermissionQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(SysRolePermissionUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(SysRolePermissionAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<SysRolePermissionAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result getRolePermissionIds(SysRolePermissionQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<Integer> count(SysRolePermissionQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<Boolean> delRolePermission(SysRolePermissionEvent event) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<List<PermissionRoleDTO>> getPermissionRoles() {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
