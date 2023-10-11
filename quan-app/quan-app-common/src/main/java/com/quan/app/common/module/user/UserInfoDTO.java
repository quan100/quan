package com.quan.app.common.module.user;

import com.quan.common.base.message.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表
 *
 * @author wangquan
 * @version 1.0.0
 * @date 2020-12-27 17:50:38
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserInfoDTO extends BasePage implements Serializable {
    private static final long serialVersionUID = 4476809992009468988L;
    /**
     *
     */
    private Long id;
    /**
     * 用户表业务主键
     */
    private String userId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别
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
     * 通讯电话
     */
    private String phone;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 用户签名
     */
    private String signContent;
    /**
     * 用户头像地址
     */
    private String avatar;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    private List<UserRoleDTO> roles;

    /**
     * 权限路径列表
     */
    private List<String> paths;
}
