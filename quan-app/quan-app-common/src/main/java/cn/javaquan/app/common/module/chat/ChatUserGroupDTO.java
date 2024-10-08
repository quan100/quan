package cn.javaquan.app.common.module.chat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户群组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ChatUserGroupDTO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 群组ID.
     */
    private String groupId;

    /**
     * 是否免打扰.
     */
    private Boolean disturb;

    /**
     * 是否置顶.
     */
    private Boolean topping;

    /**
     * 用户ID.
     */
    private String userId;

    /**
     * 0：群员，1：群主，2：群管理员.
     */
    private Integer groupLeader;

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

}
