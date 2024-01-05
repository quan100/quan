package cn.javaquan.tools.dingtalk.api.ding;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author javaquan
 */
@Getter
@AllArgsConstructor
public enum MsgTypeEnum {

    /**
     * 文本类型
     */
    TEXT("text");

    /**
     * 消息类型
     */
    private final String type;

}
