package com.quan.app.service.chat.feign.fallback;

import com.quan.app.common.module.chat.ChatUserFriendAddCommand;
import com.quan.app.common.module.chat.ChatUserFriendDTO;
import com.quan.app.common.module.chat.ChatUserFriendQuery;
import com.quan.app.common.module.chat.ChatUserFriendUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.service.chat.feign.ChatUserFriendRepositoryFeign;
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
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ChatUserFriendUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ChatUserFriendAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ChatUserFriendAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result queryByUserId(String userId) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
