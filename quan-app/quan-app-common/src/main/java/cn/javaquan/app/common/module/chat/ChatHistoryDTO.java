package cn.javaquan.app.common.module.chat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天记录表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ChatHistoryDTO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 用户ID.
     */
    private String userId;

    /**
     * 好友用户ID.
     */
    private String friendUserId;

    /**
     * 聊天内容.
     */
    private String content;

    /**
     * 发送时间.
     */
    private Date sendTime;

    /**
     * 删除状态.
     */
    private Boolean delFlag;

}
