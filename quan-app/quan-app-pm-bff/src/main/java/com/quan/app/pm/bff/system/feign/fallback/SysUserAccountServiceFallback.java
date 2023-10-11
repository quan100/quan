package com.quan.app.pm.bff.system.feign.fallback;

import com.quan.app.common.module.auth.AuthQuery;
import com.quan.app.common.module.system.SysUserAccountAddCommand;
import com.quan.app.common.module.system.SysUserAccountDTO;
import com.quan.app.common.module.system.SysUserAccountQuery;
import com.quan.app.common.module.system.SysUserAccountUpdateCommand;
import com.quan.app.common.module.system.*;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.feign.SysUserAccountServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户账号
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Slf4j
@Component
public class SysUserAccountServiceFallback implements FallbackFactory<SysUserAccountServiceFeign> {

    @Override
    public SysUserAccountServiceFeign create(Throwable throwable) {
        return new SysUserAccountServiceFeign() {
            @Override
            public Result page(SysUserAccountQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(SysUserAccountUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(SysUserAccountAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<SysUserAccountAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result getUserPermission(AuthQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<SysUserAccountDTO> getUserAccount(SysUserAccountQuery query) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
