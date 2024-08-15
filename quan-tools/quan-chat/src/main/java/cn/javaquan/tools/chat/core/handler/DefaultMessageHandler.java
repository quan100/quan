package cn.javaquan.tools.chat.core.handler;

import cn.javaquan.tools.chat.core.message.ChatMessage;
import cn.javaquan.tools.chat.core.message.MessageTemplate;
import cn.javaquan.tools.chat.core.support.AbstractMessageHandler;
import cn.javaquan.tools.chat.core.support.IMessageHandler;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * 默认的消息处理器.
 * <p>
 * 接入SDK后，请自定义实现{@link IMessageHandler}接口
 *
 * @author javaquan
 * @since 1.0.0
 */
@Component
public class DefaultMessageHandler extends AbstractMessageHandler implements IMessageHandler {

    private static final Log logger = LogFactory.getLog(DefaultMessageHandler.class);

    @Override
    public void handler(ChannelHandlerContext ctx, MessageTemplate messageTemplate) {
        logger.warn("Chat server is no message handler implementation!");
        sendMessage(ctx.channel(),
                ChatMessage.toSystemMessage("A100", "Chat server is no message handler implementation!"));
    }

}
