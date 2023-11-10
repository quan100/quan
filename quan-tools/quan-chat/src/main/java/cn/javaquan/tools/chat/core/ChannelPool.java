package cn.javaquan.tools.chat.core;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 存放Channel状态信息池
 *
 * @author javaquan
 */
public class ChannelPool implements InitializingBean {

    private final Map<String, Channel> channelContainer;
    private static ChannelPool channelPool;
    public static final AttributeKey<String> SESSION_STATE = AttributeKey.valueOf("session_state");

    public ChannelPool(Map<String, Channel> channelContainer) {
        this.channelContainer = channelContainer;
        channelPool = this;
    }

    public static Map<String, Channel> getChannelContainer() {
        return channelPool.channelContainer;
    }

    public static Channel getChannel(String key) {
        return getChannelContainer().get(key);
    }

    public static Channel addChannel(String key, Channel channel) {
        return getChannelContainer().put(key, channel);
    }

    public static void removeChannel(Channel channel) {
        if (!hasSessionState(channel)) {
            return;
        }
        String userId = getSessionState(channel);
        getChannelContainer().remove(userId);
    }

    /**
     * 获取Channel中保存的状态信息
     *
     * @param channel
     * @return
     */
    public static String getSessionState(Channel channel) {
        return channel.attr(SESSION_STATE).get();
    }

    /**
     * 检查会话状态信息
     *
     * @param channel
     * @return
     */
    public static boolean hasSessionState(Channel channel) {
        return channel.hasAttr(SESSION_STATE);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.channelContainer, "[Assertion failed channelContainer] - this numeric argument must have value; it must not be null");
    }
}
