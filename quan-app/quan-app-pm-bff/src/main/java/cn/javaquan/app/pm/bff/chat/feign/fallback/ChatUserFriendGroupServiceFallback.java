package cn.javaquan.app.pm.bff.chat.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import cn.javaquan.app.pm.bff.chat.feign.ChatUserFriendGroupServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户好友分组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Slf4j
@Component
public class ChatUserFriendGroupServiceFallback implements FallbackFactory<ChatUserFriendGroupServiceFeign> {

    @Override
    public ChatUserFriendGroupServiceFeign create(Throwable throwable) {
        return new ChatUserFriendGroupServiceFeign() {
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
        };
    }
}
