package cn.javaquan.app.core.chat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户好友分组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("chat_user_friend_group")
public class ChatUserFriendGroupPO extends Model<ChatUserFriendGroupPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分组ID.
     */
    private String groupId;

    /**
     * 分组名称.
     */
    private String groupName;

    /**
     * 用户ID.
     */
    private String userId;

    /**
     * 状态.
     */
    @TableField("`status`")
    private String status;

    /**
     * 是否免打扰.
     */
    private Boolean disturb;

    /**
     * 是否置顶.
     */
    private Boolean topping;

    /**
     * 排序.
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 删除状态.
     */
    @TableLogic
    private Boolean delFlag;

}
