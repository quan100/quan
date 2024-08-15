package cn.javaquan.app.common.module.system;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class SysUserAccountUpdateCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 主键.
     */
    @NotNull(message = "ID不可为空")
    private Long id;

    /**
     * 用户ID.
     */
    private transient String userId;

    /**
     * 账号.
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 登录密码.
     */
    private String password;

    /**
     * 登录类型（1：普通用户；2：会员用户）.
     */
    private Integer type;

    /**
     * 状态（0：正常，1：冻结，2：注销）.
     */
    private Integer status;

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
