package cn.javaquan.security.common.dto.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 认证信息实体类.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class AuthEntity implements Serializable {

    private static final long serialVersionUID = 2650733007508442345L;

    /**
     * 访问者凭证，一般为访问者唯一身份标识，如：userId.
     */
    @NotBlank(message = "凭证ID不可为空")
    private String authId;

    /**
     * 访问应用类型.
     */
    private String appType;

    /**
     * 是否登录.
     */
    private boolean login;

    /**
     * 保存到凭证的数据.
     */
    private Object data;

    /**
     * 拥有的角色.
     */
    private List<?> roles;

    /**
     * 登录用户创建认证信息.
     * @param authId 访问者凭证
     * @param appType 应用服务类型
     * @param roles 角色列表
     * @param data 保存到凭证的数据
     * @return 用户认证信息
     */
    public static AuthEntity toAuthEntity(String authId, String appType, List<?> roles, Object data) {
        AuthEntity authEntity = new AuthEntity();
        authEntity.setAuthId(authId);
        authEntity.setData(data);
        authEntity.setLogin(true);
        authEntity.setAppType(appType);
        authEntity.setRoles(roles);
        return authEntity;
    }

    /**
     * 未登录用户创建认证信息.
     * @param appType 应用服务类型
     * @return 用户认证信息
     */
    public static AuthEntity toAuthEntity(String appType) {
        AuthEntity authEntity = new AuthEntity();
        authEntity.setLogin(false);
        authEntity.setAppType(appType);
        return authEntity;
    }

}
