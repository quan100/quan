package cn.javaquan.app.common.module.chat.layim;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 我的信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class MineInfoVo implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 我的信息.
     */
    private MineVO mine;

    /**
     * 好友列表.
     */
    private List<MineFriendGroupVO> friend;

    /**
     * 我的群组列表.
     */
    private List<MineGroupVO> group;

}
