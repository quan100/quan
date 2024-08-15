package cn.javaquan.app.service.chat.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.chat.ChatGroupInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatGroupInfoQuery;
import cn.javaquan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import cn.javaquan.app.service.chat.feign.ChatGroupInfoRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 群组信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ChatGroupInfoRepositoryFallback implements FallbackFactory<ChatGroupInfoRepositoryFeign> {

    @Override
    public ChatGroupInfoRepositoryFeign create(Throwable throwable) {
        return new ChatGroupInfoRepositoryFeign() {
            @Override
            public Result page(ChatGroupInfoQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ChatGroupInfoUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ChatGroupInfoAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ChatGroupInfoAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
