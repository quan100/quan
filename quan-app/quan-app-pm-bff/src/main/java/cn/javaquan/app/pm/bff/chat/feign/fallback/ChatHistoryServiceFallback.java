package cn.javaquan.app.pm.bff.chat.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.chat.ChatHistoryAddCommand;
import cn.javaquan.app.common.module.chat.ChatHistoryQuery;
import cn.javaquan.app.common.module.chat.ChatHistoryUpdateCommand;
import cn.javaquan.app.pm.bff.chat.feign.ChatHistoryServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 聊天记录表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ChatHistoryServiceFallback implements FallbackFactory<ChatHistoryServiceFeign> {

    @Override
    public ChatHistoryServiceFeign create(Throwable throwable) {
        return new ChatHistoryServiceFeign() {
            @Override
            public Result page(ChatHistoryQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ChatHistoryUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ChatHistoryAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ChatHistoryAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
