package cn.javaquan.tools.chat.server;

import cn.javaquan.tools.chat.autoconfigure.ChatProperties;
import cn.javaquan.tools.chat.context.ClientInboundHandler;
import cn.javaquan.tools.chat.context.TextWebSocketFrameHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 初始化服务配置
 *
 * @author javaquan
 */
public class ChatServerInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup group;
    private final ChatProperties properties;

    public ChatServerInitializer(ChannelGroup group, ChatProperties properties) {
        this.group = group;
        this.properties = properties;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(properties.getMaxContentLength()));
        pipeline.addLast(new IdleStateHandler(properties.getReaderIdleTime(), 0, 0, TimeUnit.SECONDS));

        pipeline.addLast(new ClientInboundHandler(group, properties.getWebsocketPath()));
        pipeline.addLast(new TextWebSocketFrameHandler());

        pipeline.addLast(new WebSocketServerProtocolHandler(properties.getWebsocketPath(), null, true, properties.getMaxFrameSize()));
    }
}
