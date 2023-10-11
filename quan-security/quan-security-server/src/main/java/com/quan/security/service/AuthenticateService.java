package com.quan.security.service;


import com.quan.security.common.dto.entity.AuthEntity;
import com.quan.security.common.dto.request.AuthenticateRequest;
import com.quan.security.common.dto.response.AuthenticateResponse;

/**
 * @author wangquan
 * @date 2020/3/10 00:45
 */
public interface AuthenticateService {

    /**
     * 认证
     *
     * @param request
     * @return
     */
    AuthenticateResponse authenticate(AuthenticateRequest request);

    /**
     * 获取token
     *
     * @param authEntity
     * @return
     */
    String getToken(AuthEntity authEntity);

    /**
     * 清除token
     *
     * @param token
     * @return
     */
    void cleanToken(String token);
}
