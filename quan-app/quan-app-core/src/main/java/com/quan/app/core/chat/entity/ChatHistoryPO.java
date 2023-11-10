package com.quan.app.core.chat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 聊天记录表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("chat_history")
public class ChatHistoryPO extends Model<ChatHistoryPO> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
