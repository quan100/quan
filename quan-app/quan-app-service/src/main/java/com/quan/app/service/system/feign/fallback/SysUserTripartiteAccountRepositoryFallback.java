package com.quan.app.service.system.feign.fallback;

import com.quan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import com.quan.app.common.module.system.SysUserTripartiteAccountQuery;
import com.quan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.feign.SysUserTripartiteAccountRepositoryFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户第三方账户
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Slf4j
@Component
public class SysUserTripartiteAccountRepositoryFallback implements FallbackFactory<SysUserTripartiteAccountRepositoryFeign> {

    @Override
    public SysUserTripartiteAccountRepositoryFeign create(Throwable throwable) {
        return new SysUserTripartiteAccountRepositoryFeign() {
            @Override
            public Result page(SysUserTripartiteAccountQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(SysUserTripartiteAccountUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(SysUserTripartiteAccountAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<SysUserTripartiteAccountAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result getByEmail(String email) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result getByTripartite(SysUserTripartiteAccountQuery query) {
                return Result.fail(throwable.getMessage());
            }

        };
    }
}
