package cn.javaquan.tools.chat.core;

import cn.javaquan.tools.chat.core.support.IMessageHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息处理器工厂
 *
 * @author wangquan
 * @date 2021/12/24 14:38
 */
@Component
public class MessageHandlerFactory {

    private final static String SUFFIX = "MessageHandler";
    private final Map<String, IMessageHandler> messageHandlerContainer;

    public MessageHandlerFactory(Map<String, IMessageHandler> messageHandlerContainer) {
        this.messageHandlerContainer = messageHandlerContainer;
    }

    /**
     * 获取消息处理器
     *
     * @param type 消息类型
     * @return
     */
    public IMessageHandler getService(String type) {
        IMessageHandler messageHandler = messageHandlerContainer.get(messageHandlerBeanName(type));
        if (null == messageHandler) {
            return messageHandlerContainer.get("defaultMessageHandler");
        }
        return messageHandler;
    }

    /**
     * 获取消息处理器的BeanName
     *
     * @param type
     * @return
     */
    private String messageHandlerBeanName(String type) {
        StringBuffer sb = new StringBuffer();
        sb.append(type.toLowerCase());
        sb.append(SUFFIX);
        return sb.toString();
    }

}
