package cn.javaquan.tools.chat.core;

import cn.javaquan.tools.chat.core.support.IMessageHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息处理器工厂.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Component
public class MessageHandlerFactory {

    private static final String SUFFIX = "MessageHandler";

    private final Map<String, IMessageHandler> messageHandlerContainer;

    /**
     * 构造消息处理器.
     * @param messageHandlerContainer 消息处理器容器
     */
    public MessageHandlerFactory(Map<String, IMessageHandler> messageHandlerContainer) {
        this.messageHandlerContainer = messageHandlerContainer;
    }

    /**
     * 获取消息处理器.
     * @param type 消息类型
     * @return 消息处理器
     */
    public IMessageHandler getService(String type) {
        IMessageHandler messageHandler = this.messageHandlerContainer.get(messageHandlerBeanName(type));
        if (null == messageHandler) {
            return this.messageHandlerContainer.get("defaultMessageHandler");
        }
        return messageHandler;
    }

    /**
     * 获取消息处理器的BeanName.
     * @param type 消息类型
     * @return 消息处理器的BeanName
     */
    private String messageHandlerBeanName(String type) {
        StringBuffer sb = new StringBuffer();
        sb.append(type.toLowerCase());
        sb.append(SUFFIX);
        return sb.toString();
    }

}
