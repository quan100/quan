package cn.javaquan.tools.chat.core.message;

import cn.javaquan.tools.chat.core.MessageTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * 接收聊天消息的结构
 *
 * @author javaquan
 */
@Data
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 6831726205975194667L;

    /**
     * 消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
     */
    private String id;

    /**
     * 消息来源用户名
     */
    private String username;

    /**
     * 聊天窗口来源类型，从发送消息传递的to里面获取
     */
    private String type;

    /**
     * 消息来源用户头像
     */
    private String avatar;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息id，可不传。除非你要对消息进行一些操作（如撤回）
     */
    private String cid;

    /**
     * 是否我发送的消息，如果为true，则会显示在右方
     */
    private Boolean mine;

    /**
     * 消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
     */
    private String fromid;

    /**
     * 服务端时间戳毫秒数。注意：如果你返回的是标准的 unix 时间戳，记得要 *1000
     */
    private Long timestamp;

    /**
     * 是否是系统消息
     */
    private Boolean system;

    /**
     * 发送系统通知消息
     *
     * @param id      聊天窗口ID
     * @param message 消息内容
     * @return
     */
    public static ChatMessage toSystemMessage(String id, String message) {
        ChatMessage messageEvent = new ChatMessage();
        messageEvent.setId(id);
        messageEvent.setType(MessageTypeEnum.SYSTEM_NOTIFICATION.getType());
        messageEvent.setContent(message);
        messageEvent.setTimestamp(System.currentTimeMillis());
        messageEvent.setSystem(true);
        return messageEvent;
    }

    /**
     * 转换消息内容发送给接收者
     *
     * @param messageTemplate
     * @return
     */
    public static ChatMessage toMessageEvent(MessageTemplate messageTemplate) {
        cn.javaquan.tools.chat.core.message.Data data = messageTemplate.getData();
        Mine mine = data.getMine();
        ChatMessage messageEvent = new ChatMessage();
        messageEvent.setId(mine.getId());
        messageEvent.setUsername(mine.getUsername());
        messageEvent.setType(messageTemplate.getType());
        messageEvent.setContent(mine.getContent());
        messageEvent.setMine(false);
        messageEvent.setCid(UUID.randomUUID().toString());
        return messageEvent;
    }

}
