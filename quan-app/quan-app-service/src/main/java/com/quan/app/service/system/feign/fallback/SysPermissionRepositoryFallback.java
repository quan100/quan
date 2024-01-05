package com.quan.app.service.system.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.system.*;
import com.quan.app.service.system.feign.SysPermissionRepositoryFeign;
import com.quan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统资源权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Slf4j
@Component
public class SysPermissionRepositoryFallback implements FallbackFactory<SysPermissionRepositoryFeign> {

    @Override
    public SysPermissionRepositoryFeign create(Throwable throwable) {
        return new SysPermissionRepositoryFeign() {
            @Override
            public Result page(SysPermissionQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(SysPermissionUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(SysPermissionAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<SysPermissionAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getRolePermission(SysRolePermissionQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getSubsetPermissions(SubsetPermissionsQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getTreePermissions(SubsetPermissionsQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<List<SysPermissionDTO>> getPerms() {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
