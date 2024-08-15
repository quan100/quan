package cn.javaquan.app.common.module.user;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表.
 *
 * @author wangquan
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserAccountDTO extends BasePage implements Serializable {

    private static final long serialVersionUID = 2467869460436043642L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 业务主键.
     */
    private String userId;

    /**
     * 账号.
     */
    private String account;

    /**
     * 盐.
     */
    private String credentialsSalt;

    /**
     * 登录凭证.
     */
    private String secret;

    /**
     * 账号类型，1：前台用户，2：后台用户.
     */
    private Integer type;

    /**
     * 状态，0：正常，1：删除.
     */
    private Integer status;

    /**
     * 创建人.
     */
    private String createUser;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 创建时间.
     */
    private Date createTime;

}
