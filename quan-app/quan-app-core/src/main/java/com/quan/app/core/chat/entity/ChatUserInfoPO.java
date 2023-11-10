package com.quan.app.core.chat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户信息表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("chat_user_info")
public class ChatUserInfoPO extends Model<ChatUserInfoPO> {
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
     * 用户名
     */
    private String userName;

    /**
     * online：在线；offline：离线
     */
    @TableField("`status`")
    private String status;

    /**
     * 签名信息
     */
    private String sign;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除状态
     */
    private Boolean delFlag;

}
