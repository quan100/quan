package cn.javaquan.app.common.module.chat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户好友分组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
public class ChatUserFriendGroupDTO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 分组ID
     */
    private String groupId;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 用户ID
     */
    private String userId;

    /**
     *
     */
    private String status;

    /**
     * 是否免打扰
     */
    private Boolean disturb;

    /**
     * 是否置顶
     */
    private Boolean topping;

    /**
     * 排序
     */
    private Integer order;

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
