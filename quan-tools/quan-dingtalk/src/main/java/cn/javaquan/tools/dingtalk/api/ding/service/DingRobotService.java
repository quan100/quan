package cn.javaquan.tools.dingtalk.api.ding.service;

import cn.javaquan.tools.dingtalk.api.ding.MsgTypeEnum;
import cn.javaquan.tools.dingtalk.api.ding.entity.DingRobotTextMsgCommand;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义钉钉机器人消息推送
 *
 * @author javaquan
 **/
public class DingRobotService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DingRobotService.class);

    /**
     * text 类型消息推送
     *
     * @param cmd
     * @return
     */
    @SneakyThrows
    public boolean sendText(DingRobotTextMsgCommand cmd) {
        DingTalkClient client = new DefaultDingTalkClient(cmd.getTo());
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        // 消息类型，固定为：text
        request.setMsgtype(MsgTypeEnum.TEXT.getType());

        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        // 消息内容
        text.setContent(cmd.getContent());
        request.setText(text);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        // 被@人的手机号（在content里添加@人的手机号）
        at.setAtMobiles(cmd.getAtMobiles());
        // 是否@所有人
        at.setIsAtAll(cmd.getIsAtAll());
        request.setAt(at);

        OapiRobotSendResponse response = client.execute(request);
        if (response.getErrcode() == 0) {
            return true;
        } else {
            LOGGER.warn(response.getErrmsg());
            return false;
        }
    }

}
