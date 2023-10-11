package com.quan.app.pm.bff.system.feign.fallback;

import com.quan.app.common.module.system.SysUserInfoAddCommand;
import com.quan.app.common.module.system.SysUserInfoDTO;
import com.quan.app.common.module.system.SysUserInfoQuery;
import com.quan.app.common.module.system.SysUserInfoUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.feign.SysUserInfoServiceFeign;
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
public class SysUserInfoServiceFallback implements FallbackFactory<SysUserInfoServiceFeign> {

    @Override
    public SysUserInfoServiceFeign create(Throwable throwable) {
        return new SysUserInfoServiceFeign() {
            @Override
            public Result page(SysUserInfoQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(SysUserInfoUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(SysUserInfoAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<SysUserInfoAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<SysUserInfoDTO> getUserInfo(String userId) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
