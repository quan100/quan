package cn.javaquan.security.service;


import cn.javaquan.security.common.dto.entity.AuthEntity;
import cn.javaquan.security.common.dto.request.AuthenticateRequest;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;

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
