package com.quan.app.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户角色配置
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
public class SysUserRolePO extends Model<SysUserRolePO> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private String userId;

}
