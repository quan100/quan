package cn.javaquan.app.common.module.user;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表.
 *
 * @author wangquan
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserInfoDTO extends BasePage implements Serializable {

    private static final long serialVersionUID = 4476809992009468988L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 用户表业务主键.
     */
    private String userId;

    /**
     * 昵称.
     */
    private String nickName;

    /**
     * 性别.
     */
    private Integer sex;

    /**
     * 通讯地址.
     */
    private String address;

    /**
     * 通讯邮箱.
     */
    private String email;

    /**
     * 通讯电话.
     */
    private String phone;

    /**
     * 身份证.
     */
    private String idCard;

    /**
     * 用户签名.
     */
    private String signContent;

    /**
     * 用户头像地址.
     */
    private String avatar;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 角色列表.
     */
    private List<UserRoleDTO> roles;

    /**
     * 权限路径列表.
     */
    private List<String> paths;

}
