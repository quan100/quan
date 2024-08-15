package cn.javaquan.tools.chat.server;

import cn.javaquan.tools.chat.autoconfigure.ChatProperties;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * 初始化服务配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
public class SecureChatServerInitializer extends ChatServerInitializer {

    private final SslContext context;

    /**
     * 构造带凭证的初始化器.
     * @param group 通道组
     * @param context 安全协议上下文
     * @param properties 聊天服务配置
     */
    public SecureChatServerInitializer(ChannelGroup group, SslContext context, ChatProperties properties) {
        super(group, properties);
        this.context = context;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        super.initChannel(ch);
        SSLEngine engine = this.context.newEngine(ch.alloc());
        engine.setUseClientMode(false);
        ch.pipeline().addFirst(new SslHandler(engine));
    }

}
