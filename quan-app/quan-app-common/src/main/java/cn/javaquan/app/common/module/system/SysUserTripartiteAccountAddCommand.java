package cn.javaquan.app.common.module.system;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户第三方账户
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class SysUserTripartiteAccountAddCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 账号
     */
    private String account;

    /**
     * 第三方类型
     */
    @NotBlank(message = "第三方类型不能为空")
    private String thirdType;

    /**
     * 第三方ID
     */
    @NotBlank(message = "第三方ID不能为空")
    private String thirdId;

    /**
     * 绑定状态，0：未绑定，1：已绑定
     */
    @NotNull(message = "绑定状态不能为空")
    private Integer bindStatus;

    /**
     * 状态（0：正常，1：冻结，2：注销, 3：账号申请中）
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

}
