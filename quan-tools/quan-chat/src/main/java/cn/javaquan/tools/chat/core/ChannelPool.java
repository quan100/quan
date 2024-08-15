package cn.javaquan.tools.chat.core;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 存放Channel状态信息池.
 *
 * @author javaquan
 * @since 1.0.0
 */
public class ChannelPool implements InitializingBean {

    /**
     * 通道容器.
     */
    private final Map<String, Channel> channelContainer;

    /**
     * 注入静态的实例.
     */
    private static ChannelPool channelPool;

    /**
     * 会话状态信息.
     */
    public static final AttributeKey<String> SESSION_STATE = AttributeKey.valueOf("session_state");

    /**
     * 构造方法.
     * @param channelContainer 通道容器
     */
    public ChannelPool(Map<String, Channel> channelContainer) {
        this.channelContainer = channelContainer;
        channelPool = this;
    }

    /**
     * 获取会话通道容器.
     * @return 通道容器
     */
    public static Map<String, Channel> getChannelContainer() {
        return channelPool.channelContainer;
    }

    /**
     * 获取会话通道.
     * @param key 键值
     * @return 从通道容器中获取的通道
     */
    public static Channel getChannel(String key) {
        return getChannelContainer().get(key);
    }

    /**
     * 添加会话通道.
     * @param key 键值
     * @param channel 绑定到上下文的通道
     * @return 添加到通道容器中的通道
     */
    public static Channel addChannel(String key, Channel channel) {
        return getChannelContainer().put(key, channel);
    }

    /**
     * 移除会话通道.
     * @param channel 绑定到上下文的通道
     */
    public static void removeChannel(Channel channel) {
        if (!hasSessionState(channel)) {
            return;
        }
        String userId = getSessionState(channel);
        getChannelContainer().remove(userId);
    }

    /**
     * 获取Channel中保存的状态信息.
     * @param channel 绑定到上下文的通道
     * @return 保存到通道中的状态信息
     */
    public static String getSessionState(Channel channel) {
        return channel.attr(SESSION_STATE).get();
    }

    /**
     * 检查会话状态信息.
     * @param channel 绑定到上下文的通道
     * @return 是否存在会话状态信息
     */
    public static boolean hasSessionState(Channel channel) {
        return channel.hasAttr(SESSION_STATE);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.channelContainer,
                "[Assertion failed channelContainer] - this numeric argument must have value; it must not be null");
    }

}
