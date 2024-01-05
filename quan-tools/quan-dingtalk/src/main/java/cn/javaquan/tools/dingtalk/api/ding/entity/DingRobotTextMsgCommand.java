package cn.javaquan.tools.dingtalk.api.ding.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author javaquan
 */
@Data
public class DingRobotTextMsgCommand implements Serializable {

    private static final long serialVersionUID = 4883083786916142577L;

    /**
     * 接受者
     */
    private String to;

    /**
     * 内容
     */
    private String content;

    /**
     * 被@人的手机号（在content里添加@人的手机号）
     */
    private List<String> atMobiles;

    /**
     * 是否@所有人
     */
    private Boolean isAtAll;
}
