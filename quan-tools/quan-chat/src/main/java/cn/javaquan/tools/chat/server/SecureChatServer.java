package cn.javaquan.tools.chat.server;

import cn.javaquan.tools.chat.autoconfigure.ChatProperties;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;

/**
 * 带凭证的聊天服务.
 *
 * @author javaquan
 * @since 1.0.0
 */
public class SecureChatServer extends ChatServer {

    private final SslContext context;

    /**
     * 构造方法.
     *
     * 注入 ssl context
     * @param context ssl context
     */
    public SecureChatServer(SslContext context) {
        this.context = context;
    }

    @Override
    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group, ChatProperties properties) {
        return new SecureChatServerInitializer(group, this.context, properties);
    }

}
