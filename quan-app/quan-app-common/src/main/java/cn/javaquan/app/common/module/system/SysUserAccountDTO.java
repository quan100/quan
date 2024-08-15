package cn.javaquan.app.common.module.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户账号.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class SysUserAccountDTO implements Serializable {

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
     * 盐.
     */
    private String salt;

    /**
     * 登录凭证.
     */
    private String secret;

    /**
     * 登录类型（1：普通用户；2：会员用户）.
     */
    private Integer type;

    /**
     * 状态（0：正常，1：冻结，2：注销）.
     */
    private Integer status;

    /**
     * 删除状态，false：未删除，true：已删除.
     */
    private Boolean delFlag;

    /**
     * 创建人.
     */
    private String createUser;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新人.
     */
    private String updateUser;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 应用编码，标识权限所属的应用.
     */
    private String appType;

    /**
     * 角色ID.
     */
    private List<Long> roleIdList;

}
