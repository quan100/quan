package com.quan.app.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_info")
public class SysUserInfoPO extends Model<SysUserInfoPO> {
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
    private String nickName;

    /**
     * 性别（0：保密）
     */
    private Integer sex;

    /**
     * 通讯地址
     */
    private String address;

    /**
     * 通讯邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 个人描述
     */
    private String description;

    /**
     * 通讯电话
     */
    private String phone;

    /**
     * 用户头像地址
     */
    private String avatar;

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

}
