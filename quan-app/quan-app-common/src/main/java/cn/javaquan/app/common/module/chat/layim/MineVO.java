package cn.javaquan.app.common.module.chat.layim;

import lombok.Data;

import java.io.Serializable;

/**
 * 我的信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class MineVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 用户ID.
     */
    private String id;

    /**
     * 用户名.
     */
    private String username;

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

}
