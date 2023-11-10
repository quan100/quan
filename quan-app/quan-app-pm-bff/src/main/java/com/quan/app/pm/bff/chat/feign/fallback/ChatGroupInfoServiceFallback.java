package com.quan.app.pm.bff.chat.feign.fallback;

import com.quan.app.common.module.chat.ChatGroupInfoAddCommand;
import com.quan.app.common.module.chat.ChatGroupInfoQuery;
import com.quan.app.common.module.chat.ChatGroupInfoUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.chat.feign.ChatGroupInfoServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 群组信息
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Slf4j
@Component
public class ChatGroupInfoServiceFallback implements FallbackFactory<ChatGroupInfoServiceFeign> {

    @Override
    public ChatGroupInfoServiceFeign create(Throwable throwable) {
        return new ChatGroupInfoServiceFeign() {
            @Override
            public Result page(ChatGroupInfoQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ChatGroupInfoUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ChatGroupInfoAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ChatGroupInfoAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
