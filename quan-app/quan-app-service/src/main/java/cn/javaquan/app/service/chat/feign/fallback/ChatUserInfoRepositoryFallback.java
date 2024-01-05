package cn.javaquan.app.service.chat.feign.fallback;

import cn.javaquan.tools.notice.SystemNoticeException;
import cn.javaquan.app.common.module.chat.ChatUserInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserInfoDTO;
import cn.javaquan.app.common.module.chat.ChatUserInfoQuery;
import cn.javaquan.app.common.module.chat.ChatUserInfoUpdateCommand;
import cn.javaquan.app.service.chat.feign.ChatUserInfoRepositoryFeign;
import cn.javaquan.common.base.message.Result;
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
public class ChatUserInfoRepositoryFallback implements FallbackFactory<ChatUserInfoRepositoryFeign> {

    @Override
    public ChatUserInfoRepositoryFeign create(Throwable throwable) {
        return new ChatUserInfoRepositoryFeign() {
            @Override
            public Result page(ChatUserInfoQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(ChatUserInfoUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(ChatUserInfoAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<ChatUserInfoAddCommand> cmds) {
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

            @Override
            public Result<List<ChatUserInfoDTO>> queryByUserIds(List<String> userIds) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
