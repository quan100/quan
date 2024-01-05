package com.quan.app.service.base.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.base.BaseConfigAddCommand;
import com.quan.app.common.module.base.BaseConfigQuery;
import com.quan.app.common.module.base.BaseConfigUpdateCommand;
import com.quan.app.service.base.feign.BaseConfigRepositoryFeign;
import com.quan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统通用配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
@Slf4j
@Component
public class BaseConfigRepositoryFallback implements FallbackFactory<BaseConfigRepositoryFeign> {

    @Override
    public BaseConfigRepositoryFeign create(Throwable throwable) {
        return new BaseConfigRepositoryFeign() {
            @Override
            public Result page(BaseConfigQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Integer id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(BaseConfigUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(BaseConfigAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<BaseConfigAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Integer> ids) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
