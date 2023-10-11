package com.quan.app.service.tools.feign.fallback;

import com.quan.app.common.module.tools.ToolsAddCommand;
import com.quan.app.common.module.tools.ToolsQuery;
import com.quan.app.common.module.tools.ToolsUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.service.tools.feign.ToolsRepositoryFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 工具
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class ToolsRepositoryFallback implements FallbackFactory<ToolsRepositoryFeign> {

    @Override
    public ToolsRepositoryFeign create(Throwable throwable) {
        return new ToolsRepositoryFeign() {
            @Override
            public Result page(ToolsQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ToolsUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ToolsAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ToolsAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result getTools(ToolsQuery query) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
