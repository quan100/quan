package com.quan.app.mobile.bff.tools.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.tools.ToolsQuery;
import com.quan.app.mobile.bff.tools.feign.OpenToolsRepositoryFeign;
import com.quan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 工具
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class OpenToolsRepositoryFallback implements FallbackFactory<OpenToolsRepositoryFeign> {

    @Override
    public OpenToolsRepositoryFeign create(Throwable throwable) {
        return new OpenToolsRepositoryFeign() {
            @Override
            public Result page(ToolsQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getTools(ToolsQuery query) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
