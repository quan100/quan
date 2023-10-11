package com.quan.app.pm.bff.friendly.feign.fallback;

import com.quan.app.common.module.friendly.FriendlyLinkAddCommand;
import com.quan.app.common.module.friendly.FriendlyLinkQuery;
import com.quan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.friendly.feign.FriendlyLinkServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 友情链接
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class FriendlyLinkServiceFallback implements FallbackFactory<FriendlyLinkServiceFeign> {

    @Override
    public FriendlyLinkServiceFeign create(Throwable throwable) {
        return new FriendlyLinkServiceFeign() {
            @Override
            public Result page(FriendlyLinkQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(FriendlyLinkUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(FriendlyLinkAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<FriendlyLinkAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
