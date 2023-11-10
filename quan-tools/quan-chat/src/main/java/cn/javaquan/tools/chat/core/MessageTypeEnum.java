package cn.javaquan.tools.chat.core;

import lombok.AllArgsConstructor;

/**
 * 消息类型枚举
 *
 * @author javaquan
 */
@AllArgsConstructor
public enum MessageTypeEnum {

    FRIEND("friend", "好友消息"),
    GROUP("group", "群组消息"),
    SYSTEM_ADMIN("admin", "管理员消息"),
    SYSTEM_NOTIFICATION("system", "系统通知消息"),
    ;

    final String type;

    final String description;

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean eq(String type) {
        return this.name().equals(type);
    }

}
