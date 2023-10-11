package com.quan.app.pm.bff.system.feign.fallback;

import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.common.module.system.*;
import com.quan.app.pm.bff.system.feign.SysPermissionServiceFeign;
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
public class SysPermissionServiceFallback implements FallbackFactory<SysPermissionServiceFeign> {

    @Override
    public SysPermissionServiceFeign create(Throwable throwable) {
        return new SysPermissionServiceFeign() {
            @Override
            public Result page(SysPermissionQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(SysPermissionUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(SysPermissionAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<SysPermissionAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<PageResult<SysPermissionTreeDTO>> getSubsetPermissions(SubsetPermissionsQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result getTreePermissions(SubsetPermissionsQuery query) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
