package cn.javaquan.app.core.system.entity;

import cn.javaquan.tools.sensitive.FieldSensitive;
import cn.javaquan.tools.sensitive.SensitiveRuleEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户第三方账户.
 *
 * @author javaquan
 * @since 2.3.2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_tripartite_account")
public class SysUserTripartiteAccountPO extends Model<SysUserTripartiteAccountPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID.
     */
    private String userId;

    /**
     * 账号.
     */
    @FieldSensitive(rule = { SensitiveRuleEnum.PHONE, SensitiveRuleEnum.EMAIL })
    private String account;

    /**
     * 第三方类型.
     */
    private String thirdType;

    /**
     * 第三方ID.
     */
    @FieldSensitive
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
     * 删除状态，false：未删除，true：已删除.
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 创建人.
     */
    @FieldSensitive(rule = SensitiveRuleEnum.ALL)
    private String createUser;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新人.
     */
    @FieldSensitive(rule = SensitiveRuleEnum.ALL)
    private String updateUser;

    /**
     * 更新时间.
     */
    private Date updateTime;

}
