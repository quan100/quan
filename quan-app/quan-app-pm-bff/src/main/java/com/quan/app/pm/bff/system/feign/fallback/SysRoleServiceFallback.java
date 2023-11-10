package com.quan.app.pm.bff.system.feign.fallback;

import com.quan.app.common.exception.SystemException;
import com.quan.app.common.module.system.AuthorizeRolePermissionEvent;
import com.quan.app.common.module.system.SysRoleAddCommand;
import com.quan.app.common.module.system.SysRoleQuery;
import com.quan.app.common.module.system.SysRoleUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.feign.SysRoleServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Slf4j
@Component
public class SysRoleServiceFallback implements FallbackFactory<SysRoleServiceFeign> {

    @Override
    public SysRoleServiceFeign create(Throwable throwable) {
        return new SysRoleServiceFeign() {
            @Override
            public Result page(SysRoleQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(SysRoleUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(SysRoleAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<SysRoleAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public void authorizeRolePermission(AuthorizeRolePermissionEvent event) {
                throw new SystemException(throwable.getMessage());
            }
        };
    }
}
