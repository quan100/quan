package com.quan.app.common.module.chat;

import com.quan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天记录表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
public class ChatHistoryQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 好友用户ID
     */
    private String friendUserId;

    /**
     * 聊天内容
     */
    private String content;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 删除状态
     */
    private Boolean delFlag;

}
