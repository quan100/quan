package cn.javaquan.app.common.module.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户第三方账户绑定.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class TripartiteBoundEvent implements Serializable {

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
     * 第三方昵称.
     */
    public String nick;

    /**
     * 第三方头像.
     */
    public String avatar;

    /**
     * 第三方手机号码.
     */
    public String mobile;

    /**
     * 第三方绑定的邮箱.
     */
    public String email;

    /**
     * 手机号对应的国家号.
     */
    public String stateCode;

    /**
     * 绑定状态 0：未绑定 1：已绑定 2：未激活.
     */
    private Integer bindStatus;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 创建人.
     */
    private String createUser;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 创建人.
     */
    private String updateUser;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 默认绑定的角色ID.
     */
    private Long defaultBoundRole;

}
