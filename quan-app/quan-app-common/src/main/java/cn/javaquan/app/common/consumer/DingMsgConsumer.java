package cn.javaquan.app.common.consumer;

import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.tools.dingtalk.api.ding.entity.DingRobotTextMsgCommand;
import cn.javaquan.tools.dingtalk.api.ding.service.DingRobotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 钉钉消息消费
 *
 * @author javaquan
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DingMsgConsumer {

    private final DingRobotService dingRobotService;

    @JmsListener(destination = TopicEnum.DING_MSG_TOPIC)
    public void roleAuthorizationListener(DingRobotTextMsgCommand cmd) {
        try {
            dingRobotService.sendText(cmd);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
