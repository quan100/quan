package com.quan.app.common.module.chat.layim;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
public class MineFriendGroupVO implements Serializable {

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
    private String groupname;

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
     * 好友列表
     */
    private List<MineVO> list;

}
