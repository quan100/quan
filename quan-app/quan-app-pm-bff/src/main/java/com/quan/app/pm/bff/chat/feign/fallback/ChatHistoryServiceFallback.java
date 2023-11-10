package com.quan.app.pm.bff.chat.feign.fallback;

import com.quan.app.common.module.chat.ChatHistoryAddCommand;
import com.quan.app.common.module.chat.ChatHistoryQuery;
import com.quan.app.common.module.chat.ChatHistoryUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.chat.feign.ChatHistoryServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 聊天记录表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Slf4j
@Component
public class ChatHistoryServiceFallback implements FallbackFactory<ChatHistoryServiceFeign> {

    @Override
    public ChatHistoryServiceFeign create(Throwable throwable) {
        return new ChatHistoryServiceFeign() {
            @Override
            public Result page(ChatHistoryQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ChatHistoryUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ChatHistoryAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ChatHistoryAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
