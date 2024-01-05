package cn.javaquan.app.pm.bff.tools.feign.fallback;

import cn.javaquan.tools.notice.SystemNoticeException;
import cn.javaquan.app.common.module.tools.ToolsAddCommand;
import cn.javaquan.app.common.module.tools.ToolsQuery;
import cn.javaquan.app.common.module.tools.ToolsUpdateCommand;
import cn.javaquan.app.pm.bff.tools.feign.ToolsServiceFeign;
import cn.javaquan.common.base.message.Result;
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
public class ToolsServiceFallback implements FallbackFactory<ToolsServiceFeign> {

    @Override
    public ToolsServiceFeign create(Throwable throwable) {
        return new ToolsServiceFeign() {
            @Override
            public Result page(ToolsQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(ToolsUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(ToolsAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<ToolsAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}