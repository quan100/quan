package com.quan.app.service.system.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import com.quan.app.common.module.system.SysUserTripartiteAccountQuery;
import com.quan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import com.quan.app.service.system.feign.SysUserTripartiteAccountRepositoryFeign;
import com.quan.common.base.message.Result;
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
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(SysUserTripartiteAccountUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(SysUserTripartiteAccountAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<SysUserTripartiteAccountAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getByEmail(String email) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getByTripartite(SysUserTripartiteAccountQuery query) {
                throw new SystemNoticeException(throwable);
            }

        };
    }
}
