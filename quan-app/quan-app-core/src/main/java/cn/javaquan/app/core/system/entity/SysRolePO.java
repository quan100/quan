package cn.javaquan.app.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRolePO extends Model<SysRolePO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称.
     */
    @TableField("`name`")
    private String name;

    /**
     * 角色编码.
     */
    private String code;

    /**
     * 状态. 0：启用，1：禁用
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 删除状态，false：未删除，true：已删除.
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 应用编码，标识权限所属的应用.
     */
    private String appType;

}
