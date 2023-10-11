package com.quan.app.pm.bff.system.feign.fallback;

import com.quan.app.common.module.system.SysRolePermissionAddCommand;
import com.quan.app.common.module.system.SysRolePermissionQuery;
import com.quan.app.common.module.system.SysRolePermissionUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.feign.SysRolePermissionServiceFeign;
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
public class SysRolePermissionServiceFallback implements FallbackFactory<SysRolePermissionServiceFeign> {

    @Override
    public SysRolePermissionServiceFeign create(Throwable throwable) {
        return new SysRolePermissionServiceFeign() {
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
            public Result rolePermissionIds(Long id) {
                return Result.fail(throwable.getMessage());
            }

        };
    }
}
