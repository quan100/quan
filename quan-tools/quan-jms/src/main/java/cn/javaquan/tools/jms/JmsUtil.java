package cn.javaquan.tools.jms;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Destination;

/**
 * JMS工具类.
 *
 * @author wangquan
 * @since 1.0.0
 */
public class JmsUtil implements InitializingBean {

    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 构造方法.
     * @param jmsMessagingTemplate jmsMessagingTemplate
     */
    public JmsUtil(JmsMessagingTemplate jmsMessagingTemplate) {
        setJmsMessagingTemplate(jmsMessagingTemplate);
    }

    /**
     * 发送消息.
     * @param address 发送的目标地址（topic）
     * @param payload 发送的消息数据
     */
    public void send(String address, Object payload) {
        Destination destination = new ActiveMQQueue(address);
        jmsMessagingTemplate.convertAndSend(destination, payload);
    }

    /**
     * 获取JmsMessagingTemplate.
     * @return jmsMessagingTemplate
     */
    public JmsMessagingTemplate getJmsMessagingTemplate() {
        return jmsMessagingTemplate;
    }

    /**
     * 设置JmsMessagingTemplate.
     * @param jmsMessagingTemplate jmsMessagingTemplate
     */
    public void setJmsMessagingTemplate(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @Override
    public void afterPropertiesSet() {
        if (getJmsMessagingTemplate() == null) {
            throw new IllegalArgumentException("Property 'jmsMessagingTemplate' is required");
        }
    }

}
