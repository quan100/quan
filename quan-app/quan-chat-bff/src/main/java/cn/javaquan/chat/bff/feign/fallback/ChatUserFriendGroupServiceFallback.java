package cn.javaquan.chat.bff.feign.fallback;

import cn.javaquan.chat.bff.feign.ChatUserFriendGroupServiceFeign;
import com.quan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import com.quan.app.common.module.chat.ChatUserFriendGroupDTO;
import com.quan.app.common.module.chat.ChatUserFriendGroupQuery;
import com.quan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import com.quan.common.base.message.Result;
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
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ChatUserFriendGroupUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ChatUserFriendGroupAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ChatUserFriendGroupAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<List<ChatUserFriendGroupDTO>> queryByUserId(String userId) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
