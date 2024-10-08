package cn.javaquan.app.common.module.chat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户好友信息关联表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ChatUserFriendVO implements Serializable {

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
     * 好友分组ID.
     */
    private String friendGroupId;

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
    private Boolean delFlag;

    /**
     * 是否免打扰.
     */
    private Boolean disturb;

    /**
     * 是否置顶.
     */
    private Boolean topping;

}
