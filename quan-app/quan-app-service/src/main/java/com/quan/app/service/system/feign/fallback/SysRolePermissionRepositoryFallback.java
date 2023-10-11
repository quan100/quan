package com.quan.app.service.system.feign.fallback;

import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.*;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.feign.SysRolePermissionRepositoryFeign;
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
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(SysRolePermissionUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(SysRolePermissionAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<SysRolePermissionAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result getRolePermissionIds(SysRolePermissionQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<Integer> count(SysRolePermissionQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<Boolean> delRolePermission(SysRolePermissionEvent event) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<List<PermissionRoleDTO>> getPermissionRoles() {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
