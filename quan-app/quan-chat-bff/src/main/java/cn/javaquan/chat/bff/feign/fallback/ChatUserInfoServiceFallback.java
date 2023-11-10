package cn.javaquan.chat.bff.feign.fallback;

import cn.javaquan.chat.bff.feign.ChatUserInfoServiceFeign;
import com.quan.app.common.module.chat.ChatUserInfoAddCommand;
import com.quan.app.common.module.chat.ChatUserInfoDTO;
import com.quan.app.common.module.chat.ChatUserInfoQuery;
import com.quan.app.common.module.chat.ChatUserInfoUpdateCommand;
import com.quan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户信息表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Slf4j
@Component
public class ChatUserInfoServiceFallback implements FallbackFactory<ChatUserInfoServiceFeign> {

    @Override
    public ChatUserInfoServiceFeign create(Throwable throwable) {
        return new ChatUserInfoServiceFeign() {
            @Override
            public Result page(ChatUserInfoQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(ChatUserInfoUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(ChatUserInfoAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<ChatUserInfoAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<ChatUserInfoDTO> queryByUserId(String userId) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result<List<ChatUserInfoDTO>> queryByUserIds(List<String> userIds) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
