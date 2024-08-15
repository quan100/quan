package cn.javaquan.tools.chat.server;

import cn.javaquan.tools.chat.autoconfigure.ChatProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import java.net.InetSocketAddress;

/**
 * 默认的聊天服务.
 *
 * @author javaquan
 * @since 1.0.0
 */
public class ChatServer {

    private static final Log logger = LogFactory.getLog(ChatServer.class);

    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    private final EventLoopGroup group = new NioEventLoopGroup();

    private Channel channel;

    /**
     * 启动服务.
     * @param address socket地址
     * @param properties 聊天服务配置
     * @return 异步通道IO操作的结果。
     */
    public ChannelFuture start(InetSocketAddress address, ChatProperties properties) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(this.group)
            .channel(NioServerSocketChannel.class)
            .childHandler(createInitializer(this.channelGroup, properties));
        ChannelFuture future = bootstrap.bind(address);
        future.syncUninterruptibly();
        this.channel = future.channel();
        return future;
    }

    /**
     * 初始化聊天服务配置.
     * @param group 通道组
     * @param properties 聊天服务配置
     * @return 初始化聊天通道
     */
    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group, ChatProperties properties) {
        return new ChatServerInitializer(group, properties);
    }

    /**
     * 销毁服务.
     */
    public void destroy() {
        if (this.channel != null) {
            this.channel.close();
        }
        this.channelGroup.close();
        this.group.shutdownGracefully();
    }

    /**
     * 启动服务.
     * @param properties 聊天服务配置
     */
    public void start(ChatProperties properties) {
        ChannelFuture future = this.start(new InetSocketAddress(properties.getPort()), properties);
        addShutdownHook(this);
        future.addListener((listener) -> {
            Assert.isTrue(listener.isSuccess(), logMessageFormat(properties.getPort(), "error"));
            logger.info(logMessageFormat(properties.getPort(), "success"));
        });
    }

    /**
     * Registers a new virtual-machine shutdown hook.
     * @param chatServer 聊天服务
     */
    private void addShutdownHook(ChatServer chatServer) {
        Runtime.getRuntime().addShutdownHook(new Thread(chatServer::destroy));
    }

    /**
     * 日志信息格式化.
     * @param port 端口
     * @param state 服务状态
     * @return 日志信息
     */
    private String logMessageFormat(Integer port, String state) {
        return String.format("%s started %s on port(s): %s", this.getClass().getSimpleName(), state, port);
    }

}
