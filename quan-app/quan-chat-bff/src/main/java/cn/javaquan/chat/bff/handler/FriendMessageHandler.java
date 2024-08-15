package cn.javaquan.chat.bff.handler;

import cn.javaquan.tools.chat.core.message.ChatMessage;
import cn.javaquan.tools.chat.core.message.MessageTemplate;
import cn.javaquan.tools.chat.core.support.AbstractMessageHandler;
import cn.javaquan.tools.chat.core.support.IMessageHandler;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

/**
 * 好友消息处理器.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Component
public class FriendMessageHandler extends AbstractMessageHandler implements IMessageHandler {

    @Override
    public void handler(ChannelHandlerContext ctx, MessageTemplate messageTemplate) {
        /// 通知发送者，接受者不在线，发送离线消息。
        if (!sendMessage(messageTemplate)) {
            sendMessage(ctx.channel(), ChatMessage.toSystemMessage("A100", "好友不在线"));
        }
        /// TODO 保存聊天记录。kafka发送聊天记录保存事件
    }

}
