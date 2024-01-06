package cn.javaquan.app.common.exception.handler;

import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.tools.dingtalk.api.ding.entity.DingRobotTextMsgCommand;
import cn.javaquan.tools.jms.JmsUtil;
import cn.javaquan.tools.notify.ISystemExceptionNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 系统异常钉钉通知
 *
 * @author javaquan
 */
@RequiredArgsConstructor
@Component
public class SystemExceptionDingNotifyHandler implements ISystemExceptionNotification {

    private final JmsUtil jmsUtil;
    @Value("${quan.tools.notify.exception-webhook:}")
    private String to;
    @Value("${quan.tools.notify.tag:}")
    private String tag;
    @Value("${spring.profiles.active}")
    private String env;

    private final static String TEMPLATE = "[%s - %s]%s\n错误信息 ：%s";

    @Override
    public void send(Throwable e) {
        send(e.getMessage());
    }

    private void send(String text) {
        if (!StringUtils.hasText(to)) {
            return;
        }
        DingRobotTextMsgCommand cmd = new DingRobotTextMsgCommand();
        cmd.setContent(String.format(TEMPLATE, env, tag, "系统异常", text));
        cmd.setTo(to);
        jmsUtil.send(TopicEnum.DING_MSG_TOPIC, cmd);
    }

}
