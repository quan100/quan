package cn.javaquan.app.core.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户账号
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_account")
public class SysUserAccountPO extends Model<SysUserAccountPO> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 账号
     */
    private String account;

    /**
     * 盐
     */
    private String salt;

    /**
     * 登录凭证
     */
    private String secret;

    /**
     * 登录类型（1：普通用户；2：会员用户）
     */
    private Integer type;

    /**
     * 状态（0：正常，1：冻结，2：注销）
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 删除状态，false：未删除，true：已删除
     */
    @TableLogic
    private Boolean delFlag;

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

    /**
     * 应用编码，标识权限所属的应用
     */
    private String appType;

}
