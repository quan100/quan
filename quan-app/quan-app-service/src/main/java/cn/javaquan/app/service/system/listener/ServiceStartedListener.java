package cn.javaquan.app.service.system.listener;

import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.tools.jms.JmsUtil;
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
