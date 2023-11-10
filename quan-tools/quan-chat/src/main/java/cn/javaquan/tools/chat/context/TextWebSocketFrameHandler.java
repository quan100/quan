package cn.javaquan.tools.chat.context;

import cn.javaquan.tools.chat.core.MessageHandlerFactory;
import cn.javaquan.tools.chat.core.message.MessageTemplate;
import cn.javaquan.tools.chat.util.JsonUtils;
import cn.javaquan.tools.chat.util.SpringUtils;
import cn.javaquan.tools.chat.core.support.IMessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 消息处理器
 *
 * @author javaquan
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        MessageTemplate messageTemplate = messageConvertor(msg);
        messageHandler(ctx, messageTemplate);
    }

    /**
     * 消息处理
     * <p>
     * 根据消息类型处理消息
     * <p>
     * 需要自定义实现{@link IMessageHandler}接口。
     *
     * @param ctx
     * @param messageTemplate
     */
    private void messageHandler(ChannelHandlerContext ctx, MessageTemplate messageTemplate) {
        MessageHandlerFactory messageHandlerFactory = SpringUtils.getBean(MessageHandlerFactory.class);
        messageHandlerFactory.getService(messageTemplate.getType()).handler(ctx, messageTemplate);
    }

    /**
     * 将字符串信息转换为模版信息格式
     *
     * @param msg
     * @return
     */
    private MessageTemplate messageConvertor(TextWebSocketFrame msg) {
        return JsonUtils.parseObject(msg.text(), MessageTemplate.class);
    }
}
