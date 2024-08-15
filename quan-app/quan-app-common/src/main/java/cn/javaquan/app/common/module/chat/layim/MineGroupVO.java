package cn.javaquan.app.common.module.chat.layim;

import lombok.Data;

import java.io.Serializable;

/**
 * 群组.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class MineGroupVO implements Serializable {

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

}
