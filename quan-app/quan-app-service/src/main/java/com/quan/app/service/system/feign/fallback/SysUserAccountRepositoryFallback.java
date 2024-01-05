package com.quan.app.service.system.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.system.SysUserAccountAddCommand;
import com.quan.app.common.module.system.SysUserAccountQuery;
import com.quan.app.common.module.system.SysUserAccountUpdateCommand;
import com.quan.app.service.system.feign.SysUserAccountRepositoryFeign;
import com.quan.common.base.message.Result;
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
public class SysUserAccountRepositoryFallback implements FallbackFactory<SysUserAccountRepositoryFeign> {

    @Override
    public SysUserAccountRepositoryFeign create(Throwable throwable) {
        return new SysUserAccountRepositoryFeign() {
            @Override
            public Result page(SysUserAccountQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(SysUserAccountUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(SysUserAccountAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<SysUserAccountAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getUserAccount(SysUserAccountQuery query) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
