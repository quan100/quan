package com.quan.cloud.gateway.auth.filter;

import com.quan.common.base.message.Result;
import com.quan.cloud.gateway.auth.service.QuanSecurityFeign;
import com.quan.security.common.AuthConstant;
import com.quan.security.common.dto.request.AuthenticateRequest;
import com.quan.security.common.dto.response.AuthenticateResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

/**
 * 权限过滤器
 *
 * @author wangquan
 * @date 2020/3/11 16:09
 */
public abstract class AbstractAuthFilter {

    /**
     * 鉴权服务
     */
    private QuanSecurityFeign authService;

    private ServerWebExchange exchange;
    private ServerHttpRequest request;
    private ServerHttpResponse response;

    private List<String> roles;

    public QuanSecurityFeign getService() {
        return authService;
    }

    public void setService(QuanSecurityFeign authService) {
        this.authService = authService;
    }

    public ServerHttpRequest getRequest() {
        return request;
    }

    public ServerHttpResponse getResponse() {
        return response;
    }

    public ServerWebExchange getExchange() {
        return exchange;
    }

    public void setExchange(ServerWebExchange exchange) {
        this.exchange = exchange;
        this.request = exchange.getRequest();
        this.response = exchange.getResponse();
    }

    /**
     * 执行过滤器
     *
     * @return 执行状态
     */
    public abstract Result doFilter();

    /**
     * 执行过滤器
     *
     * @param roles 角色
     * @return 是否继续执行
     */
    public Result doFilter(List<String> roles) {
        return doFilter();
    }

    /**
     * 获取登录类型
     *
     * @return
     */
    public abstract int getFilterType();

    /**
     * 获取认证信息
     *
     * @param token
     * @return
     */
    protected AuthenticateResponse getAuth(String token) {
        AuthenticateRequest authenticateReq = new AuthenticateRequest();
        authenticateReq.setToken(token);
        authenticateReq.setType(getFilterType());

        // 请求失败或异常
        AuthenticateResponse authenticateRes = authService.validate(authenticateReq);
        if (authenticateRes == null) {
            return null;
        }
        return authenticateRes;
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    protected String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(AuthConstant.ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            MultiValueMap<String, HttpCookie> cookies = request.getCookies();
            if (cookies != null && !cookies.isEmpty()) {
                HttpCookie cookie = cookies.getFirst(AuthConstant.COOKIE_ACCESS_TOKEN);
                if (null != cookie) {
                    token = cookie.getValue();
                }
            }
        }

        return token;
    }

    /**
     * 获取请求IP
     *
     * @return
     */
    protected String getIp() {
        return request.getRemoteAddress().getAddress().getHostAddress();
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
