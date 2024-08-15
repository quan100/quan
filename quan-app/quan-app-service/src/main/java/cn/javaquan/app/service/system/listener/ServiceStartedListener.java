package cn.javaquan.app.service.system.listener;

import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.tools.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 服务启动监听器.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ServiceStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    private final JmsUtil jmsUtil;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        sendRoleAuthorizationInitialize();
    }

    /**
     * 初始化角色权限配置，用于鉴权.
     */
    private void sendRoleAuthorizationInitialize() {
        this.jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true);
    }

}
