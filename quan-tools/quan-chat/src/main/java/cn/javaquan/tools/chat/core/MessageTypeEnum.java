package cn.javaquan.tools.chat.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息类型枚举.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

    /**
     * 好友消息.
     */
    FRIEND("friend", "好友消息"),
    /**
     * 群组消息.
     */
    GROUP("group", "群组消息"),
    /**
     * 管理员消息.
     */
    SYSTEM_ADMIN("admin", "管理员消息"),
    /**
     * 系统通知消息.
     */
    SYSTEM_NOTIFICATION("system", "系统通知消息"),;

    /**
     * 消息类型.
     */
    final String type;

    /**
     * 消息描述.
     */
    final String description;

    /**
     * 判断消息类型是否与当前一致.
     * @param type 消息类型
     * @return 判断结果
     */
    public boolean eq(String type) {
        return this.name().equals(type);
    }

}
