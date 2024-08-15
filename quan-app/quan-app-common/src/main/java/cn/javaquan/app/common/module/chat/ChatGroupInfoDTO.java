package cn.javaquan.app.common.module.chat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 群组信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ChatGroupInfoDTO implements Serializable {

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
     * 群组名称.
     */
    private String groupName;

    /**
     * 群公告，最大200个字符.
     */
    private String groupNotice;

    /**
     * 群主用户ID.
     */
    private String userId;

    /**
     * 群成员数量限制，默认：500.
     */
    private Integer groupLimit;

    /**
     * 群成员数量.
     */
    private Integer userCount;

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
