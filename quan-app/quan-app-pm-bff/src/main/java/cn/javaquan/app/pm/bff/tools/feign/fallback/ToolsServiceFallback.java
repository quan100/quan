package cn.javaquan.app.pm.bff.tools.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
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
 * 工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ToolsServiceFallback implements FallbackFactory<ToolsServiceFeign> {

    @Override
    public ToolsServiceFeign create(Throwable throwable) {
        return new ToolsServiceFeign() {
            @Override
            public Result page(ToolsQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ToolsUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ToolsAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ToolsAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
