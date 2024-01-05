package com.quan.app.pm.bff.system.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.auth.AuthQuery;
import com.quan.app.common.module.system.SysUserAccountAddCommand;
import com.quan.app.common.module.system.SysUserAccountDTO;
import com.quan.app.common.module.system.SysUserAccountQuery;
import com.quan.app.common.module.system.SysUserAccountUpdateCommand;
import com.quan.app.pm.bff.system.feign.SysUserAccountServiceFeign;
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
public class SysUserAccountServiceFallback implements FallbackFactory<SysUserAccountServiceFeign> {

    @Override
    public SysUserAccountServiceFeign create(Throwable throwable) {
        return new SysUserAccountServiceFeign() {
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
            public Result getUserPermission(AuthQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<SysUserAccountDTO> getUserAccount(SysUserAccountQuery query) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
