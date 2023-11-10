package cn.javaquan.tools.chat.core.support;

import cn.javaquan.tools.chat.core.ChannelPool;
import cn.javaquan.tools.chat.core.message.ChatMessage;
import cn.javaquan.tools.chat.core.message.MessageTemplate;
import cn.javaquan.tools.chat.util.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 好友消息处理器抽象类
 *
 * @author javaquan
 */
public abstract class AbstractMessageHandler implements IMessageHandler {

    @Override
    public void handler(ChannelHandlerContext ctx, MessageTemplate messageTemplate) {
        sendMessage(messageTemplate);
    }

    /**
     * 向指定通道发送文本消息
     *
     * @param channel 通道
     * @param message 文本消息
     */
    protected void sendMessage(Channel channel, String message) {
        channel.writeAndFlush(new TextWebSocketFrame(message).retain());
    }

    /**
     * 向指定通道发送模版消息
     *
     * @param channel 通道
     * @param event   固定模版消息
     */
    protected void sendMessage(Channel channel, ChatMessage event) {
        String message = JsonUtils.toJSONString(event);
        sendMessage(channel, message);
    }

    /**
     * 向指定通道发送文本消息
     *
     * @param messageTemplate 客户端发送的消息体
     * @return true: 消息已发送；false: 消息未发送
     */
    protected boolean sendMessage(MessageTemplate messageTemplate) {
        // 将消息发送给接收者
        Channel channel = ChannelPool.getChannel(messageTemplate.getData().getTo().getId());
        if (null == channel) {
            return false;
        }
        sendMessage(channel, ChatMessage.toMessageEvent(messageTemplate));
        return true;
    }
}
