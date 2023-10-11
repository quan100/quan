package com.quan.tools.jms;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * JmsUtil工具配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@ConditionalOnClass({JmsUtil.class})
public class JmsUtilAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    protected static class JmsConfiguration {

        @Bean
        public JmsUtil jmsUtil(JmsMessagingTemplate jmsMessagingTemplate) {
            return new JmsUtil(jmsMessagingTemplate);
        }
    }

}
