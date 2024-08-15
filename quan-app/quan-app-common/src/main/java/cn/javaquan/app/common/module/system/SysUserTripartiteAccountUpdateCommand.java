package cn.javaquan.app.common.module.system;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户第三方账户.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class SysUserTripartiteAccountUpdateCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 主键.
     */
    @NotNull(message = "ID不可为空")
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
    @NotBlank(message = "第三方类型不能为空")
    private String thirdType;

    /**
     * 第三方ID.
     */
    @NotBlank(message = "第三方ID不能为空")
    private String thirdId;

    /**
     * 绑定状态，0：未绑定，1：已绑定.
     */
    private Integer bindStatus;

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

}
