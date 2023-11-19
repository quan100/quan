package cn.javaquan.tools.chat.core.support;

import cn.javaquan.tools.chat.core.MessageHandlerFactory;
import cn.javaquan.tools.chat.core.MessageTypeEnum;
import cn.javaquan.tools.chat.core.message.MessageTemplate;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息处理器
 * <p>
 * 消息处理器的实现必须为：消息类型（{@link MessageTypeEnum}） + {@link MessageHandlerFactory#SUFFIX}
 *
 * @author javaquan
 */
public interface IMessageHandler {

    /**
     * 处理客户端发送的消息
     *
     * @param ctx
     * @param messageTemplate
     */
    void handler(ChannelHandlerContext ctx, MessageTemplate messageTemplate);

}
