package cn.javaquan.app.common.consumer;

import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.tools.dingtalk.api.ding.entity.DingRobotTextMsgCommand;
import cn.javaquan.tools.dingtalk.api.ding.service.DingRobotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 钉钉消息消费.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DingMsgConsumer {

    private final DingRobotService dingRobotService;

    /**
     * 钉钉群机器人消息消费.
     * @param cmd 钉钉机器人消息参数
     */
    @JmsListener(destination = TopicEnum.DING_MSG_TOPIC)
    public void dingMsg(DingRobotTextMsgCommand cmd) {
        try {
            dingRobotService.sendText(cmd);
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

}
