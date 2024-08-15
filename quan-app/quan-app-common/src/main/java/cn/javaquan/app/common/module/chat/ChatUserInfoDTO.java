package cn.javaquan.app.common.module.chat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ChatUserInfoDTO implements Serializable {

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
     * 用户名.
     */
    private String userName;

    /**
     * online：在线；offline：离线.
     */
    private String status;

    /**
     * 签名信息.
     */
    private String sign;

    /**
     * 用户头像.
     */
    private String avatar;

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
