package com.quan.app.service.system.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.system.SysUserInfoAddCommand;
import com.quan.app.common.module.system.SysUserInfoQuery;
import com.quan.app.common.module.system.SysUserInfoUpdateCommand;
import com.quan.app.service.system.feign.SysUserInfoRepositoryFeign;
import com.quan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Slf4j
@Component
public class SysUserInfoRepositoryFallback implements FallbackFactory<SysUserInfoRepositoryFeign> {

    @Override
    public SysUserInfoRepositoryFeign create(Throwable throwable) {
        return new SysUserInfoRepositoryFeign() {
            @Override
            public Result page(SysUserInfoQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(SysUserInfoUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(SysUserInfoAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<SysUserInfoAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getUserInfo(String userId) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
