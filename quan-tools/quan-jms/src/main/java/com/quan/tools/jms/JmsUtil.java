package com.quan.tools.jms;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.lang.Nullable;

import javax.jms.Destination;

/**
 * @author wangquan
 */
public class JmsUtil implements InitializingBean {

    private JmsMessagingTemplate jmsMessagingTemplate;

    public JmsUtil() {
    }

    public JmsUtil(JmsMessagingTemplate jmsMessagingTemplate) {
        this();
        setJmsMessagingTemplate(jmsMessagingTemplate);
    }

    public void send(String name, Object payload) {
        Destination destination = new ActiveMQQueue(name);
        jmsMessagingTemplate.convertAndSend(destination, payload);
    }

    @Nullable
    public JmsMessagingTemplate getJmsMessagingTemplate() {
        return jmsMessagingTemplate;
    }

    public void setJmsMessagingTemplate(@Nullable JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @Override
    public void afterPropertiesSet() {
        if (getJmsMessagingTemplate() == null) {
            throw new IllegalArgumentException("Property 'jmsMessagingTemplate' is required");
        }
    }
}
