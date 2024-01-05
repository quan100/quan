package cn.javaquan.app.service.system.feign.fallback;

import cn.javaquan.app.common.module.system.*;
import cn.javaquan.app.service.system.feign.SysRolePermissionRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.notice.SystemNoticeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Slf4j
@Component
public class SysRolePermissionRepositoryFallback implements FallbackFactory<SysRolePermissionRepositoryFeign> {

    @Override
    public SysRolePermissionRepositoryFeign create(Throwable throwable) {
        return new SysRolePermissionRepositoryFeign() {
            @Override
            public Result page(SysRolePermissionQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(SysRolePermissionUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(SysRolePermissionAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<SysRolePermissionAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getRolePermissionIds(SysRolePermissionQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<Integer> count(SysRolePermissionQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<Boolean> delRolePermission(SysRolePermissionEvent event) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<List<PermissionRoleDTO>> getPermissionRoles() {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
