package cn.javaquan.app.common.module.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * 根据认证信息查询
 *
 * @author wangquan
 * @date 2020/3/10 00:03
 */
@Data
public class AuthQuery implements Serializable {
    private static final long serialVersionUID = 2650733007508442345L;

    /**
     * 访问者凭证，一般为访问者唯一身份标识，如：userId
     */
    private String authId;

    /**
     * 访问应用类型
     */
    private String appType;

    /**
     * 是否登录
     */
    private boolean login;

}
