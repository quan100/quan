package cn.javaquan.chat.bff.feign.fallback;

import cn.javaquan.chat.bff.feign.ChatUserInfoServiceFeign;
import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.chat.ChatUserInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserInfoDTO;
import cn.javaquan.app.common.module.chat.ChatUserInfoQuery;
import cn.javaquan.app.common.module.chat.ChatUserInfoUpdateCommand;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户信息表.
 *
 * Artalk 评论数据接口用于创建服务降级的备选实现.
 * <p>
 * 当远程调用失败时，会触发该工厂创建备选实现来处理异常情况.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ChatUserInfoServiceFallback implements FallbackFactory<ChatUserInfoServiceFeign> {

    @Override
    public ChatUserInfoServiceFeign create(Throwable throwable) {
        return new ChatUserInfoServiceFeign() {
            @Override
            public Result page(ChatUserInfoQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ChatUserInfoUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ChatUserInfoAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ChatUserInfoAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<ChatUserInfoDTO> queryByUserId(String userId) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<List<ChatUserInfoDTO>> queryByUserIds(List<String> userIds) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
