package cn.javaquan.app.service.chat.feign.fallback;

import cn.javaquan.tools.notice.SystemNoticeException;
import cn.javaquan.app.common.module.chat.ChatUserFriendAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserFriendQuery;
import cn.javaquan.app.common.module.chat.ChatUserFriendUpdateCommand;
import cn.javaquan.app.service.chat.feign.ChatUserFriendRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户好友信息关联表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Slf4j
@Component
public class ChatUserFriendRepositoryFallback implements FallbackFactory<ChatUserFriendRepositoryFeign> {

    @Override
    public ChatUserFriendRepositoryFeign create(Throwable throwable) {
        return new ChatUserFriendRepositoryFeign() {
            @Override
            public Result page(ChatUserFriendQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(ChatUserFriendUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(ChatUserFriendAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<ChatUserFriendAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result queryByUserId(String userId) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}