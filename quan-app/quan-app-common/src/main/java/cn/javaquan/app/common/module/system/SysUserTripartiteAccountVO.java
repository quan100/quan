package cn.javaquan.app.common.module.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户第三方账户.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class SysUserTripartiteAccountVO implements Serializable {

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
     * 账号.
     */
    private String account;

    /**
     * 第三方类型.
     */
    private String thirdType;

    /**
     * 第三方ID.
     */
    private String thirdId;

    /**
     * 绑定状态，0：未绑定，1：已绑定.
     */
    private Integer bindStatus;

    /**
     * 状态（0：正常，1：冻结，2：注销）.
     */
    private Integer status;

}
