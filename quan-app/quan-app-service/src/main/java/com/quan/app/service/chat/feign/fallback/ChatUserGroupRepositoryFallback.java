package com.quan.app.service.chat.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.chat.ChatUserGroupAddCommand;
import com.quan.app.common.module.chat.ChatUserGroupDTO;
import com.quan.app.common.module.chat.ChatUserGroupQuery;
import com.quan.app.common.module.chat.ChatUserGroupUpdateCommand;
import com.quan.app.service.chat.feign.ChatUserGroupRepositoryFeign;
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
public class ChatUserGroupRepositoryFallback implements FallbackFactory<ChatUserGroupRepositoryFeign> {

    @Override
    public ChatUserGroupRepositoryFeign create(Throwable throwable) {
        return new ChatUserGroupRepositoryFeign() {
            @Override
            public Result page(ChatUserGroupQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(ChatUserGroupUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(ChatUserGroupAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<ChatUserGroupAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<List<ChatUserGroupDTO>> queryByUserId(String userId) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
