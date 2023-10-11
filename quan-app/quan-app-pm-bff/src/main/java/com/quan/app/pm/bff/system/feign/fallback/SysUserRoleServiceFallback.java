package com.quan.app.pm.bff.system.feign.fallback;

import com.quan.app.common.module.system.SysUserRoleAddCommand;
import com.quan.app.common.module.system.SysUserRoleDTO;
import com.quan.app.common.module.system.SysUserRoleQuery;
import com.quan.app.common.module.system.SysUserRoleUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.feign.SysUserRoleServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Slf4j
@Component
public class SysUserRoleServiceFallback implements FallbackFactory<SysUserRoleServiceFeign> {

    @Override
    public SysUserRoleServiceFeign create(Throwable throwable) {
        return new SysUserRoleServiceFeign() {
            @Override
            public Result page(SysUserRoleQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(SysUserRoleUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(SysUserRoleAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<SysUserRoleAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<List<SysUserRoleDTO>> getUserRole(String userId) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<Integer> getCount(List<Long> roleIds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<Boolean> delByRoleId(List<Long> roleIds) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
