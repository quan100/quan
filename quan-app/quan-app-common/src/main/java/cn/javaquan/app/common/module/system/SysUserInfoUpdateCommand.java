package cn.javaquan.app.common.module.system;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class SysUserInfoUpdateCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 主键
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
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
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

}
