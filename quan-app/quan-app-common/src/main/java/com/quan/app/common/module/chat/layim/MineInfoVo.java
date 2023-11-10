package com.quan.app.common.module.chat.layim;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
public class MineInfoVo implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    private MineVO mine;

    private List<MineFriendGroupVO> friend;

    private List<MineGroupVO> group;
}
