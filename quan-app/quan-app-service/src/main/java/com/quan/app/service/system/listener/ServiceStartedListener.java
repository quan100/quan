package com.quan.app.service.system.listener;

import com.quan.common.base.constant.TopicEnum;
import com.quan.tools.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ServiceStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    private final JmsUtil jmsUtil;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true);
    }
}
