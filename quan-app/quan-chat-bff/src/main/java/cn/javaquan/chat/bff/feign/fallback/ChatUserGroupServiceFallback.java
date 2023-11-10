package cn.javaquan.chat.bff.feign.fallback;

import cn.javaquan.chat.bff.feign.ChatUserGroupServiceFeign;
import com.quan.app.common.module.chat.ChatUserGroupAddCommand;
import com.quan.app.common.module.chat.ChatUserGroupDTO;
import com.quan.app.common.module.chat.ChatUserGroupQuery;
import com.quan.app.common.module.chat.ChatUserGroupUpdateCommand;
import com.quan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户群组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Slf4j
@Component
public class ChatUserGroupServiceFallback implements FallbackFactory<ChatUserGroupServiceFeign> {

    @Override
    public ChatUserGroupServiceFeign create(Throwable throwable) {
        return new ChatUserGroupServiceFeign() {
            @Override
            public Result page(ChatUserGroupQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ChatUserGroupUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ChatUserGroupAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ChatUserGroupAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<List<ChatUserGroupDTO>> queryByUserId(String userId) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
