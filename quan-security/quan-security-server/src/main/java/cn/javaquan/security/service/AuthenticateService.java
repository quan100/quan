package cn.javaquan.security.service;

import cn.javaquan.security.common.dto.entity.AuthEntity;
import cn.javaquan.security.common.dto.request.AuthenticateRequest;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;

/**
 * 认证服务.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface AuthenticateService {

    /**
     * 权限认证.
     * @param request 权限认证请求参数
     * @return 权限认证结果
     */
    AuthenticateResponse authenticate(AuthenticateRequest request);

    /**
     * 获取token.
     * @param authEntity 用户登录认证信息
     * @return token
     */
    String getToken(AuthEntity authEntity);

    /**
     * 清除token.
     * @param token token
     */
    void cleanToken(String token);

}
