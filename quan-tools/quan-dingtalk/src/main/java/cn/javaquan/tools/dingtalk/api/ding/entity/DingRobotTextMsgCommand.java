package cn.javaquan.tools.dingtalk.api.ding.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 钉钉机器人文本消息发送指令参数.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class DingRobotTextMsgCommand implements Serializable {

    private static final long serialVersionUID = 4883083786916142577L;

    /**
     * 接受者.
     */
    private String to;

    /**
     * 内容.
     */
    private String content;

    /**
     * 被@人的手机号（在content里添加@人的手机号）.
     */
    private List<String> atMobiles;

    /**
     * 是否@所有人.
     */
    private Boolean isAtAll;

}
