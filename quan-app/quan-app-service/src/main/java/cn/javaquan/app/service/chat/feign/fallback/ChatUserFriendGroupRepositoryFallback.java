package cn.javaquan.app.service.chat.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import cn.javaquan.app.service.chat.feign.ChatUserFriendGroupRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户好友分组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ChatUserFriendGroupRepositoryFallback implements FallbackFactory<ChatUserFriendGroupRepositoryFeign> {

    @Override
    public ChatUserFriendGroupRepositoryFeign create(Throwable throwable) {
        return new ChatUserFriendGroupRepositoryFeign() {
            @Override
            public Result page(ChatUserFriendGroupQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ChatUserFriendGroupUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ChatUserFriendGroupAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ChatUserFriendGroupAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result queryByUserId(String userId) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
