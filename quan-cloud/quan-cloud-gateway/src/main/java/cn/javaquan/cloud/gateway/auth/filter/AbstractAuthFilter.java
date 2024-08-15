package cn.javaquan.cloud.gateway.auth.filter;

import cn.javaquan.cloud.gateway.auth.service.QuanSecurityFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.security.common.AuthConstant;
import cn.javaquan.security.common.dto.request.AuthenticateRequest;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

/**
 * 权限过滤器.
 *
 * @author wangquan
 * @since 1.0.0
 */
public abstract class AbstractAuthFilter {

    /**
     * 鉴权服务.
     */
    private QuanSecurityFeign authService;

    /**
     * HTTP请求-响应交互的契约.
     */
    private ServerWebExchange exchange;

    /**
     * 表示响应式服务器端HTTP请求.
     */
    private ServerHttpRequest request;

    /**
     * 表示响应式服务器端HTTP响应.
     */
    private ServerHttpResponse response;

    /**
     * 角色列表.
     */
    private List<String> roles;

    /**
     * 获取鉴权服务.
     * @return 鉴权
     */
    public QuanSecurityFeign getService() {
        return this.authService;
    }

    /**
     * 设置鉴权服务.
     * @param authService 鉴权服务
     */
    public void setService(QuanSecurityFeign authService) {
        this.authService = authService;
    }

    /**
     * 获取HTTP请求.
     * @return http请求
     */
    public ServerHttpRequest getRequest() {
        return this.request;
    }

    /**
     * 获取HTTP响应.
     * @return http响应
     */
    public ServerHttpResponse getResponse() {
        return this.response;
    }

    /**
     * 获取HTTP请求-响应交互的契约.
     * @return http请求-响应交互的契约
     */
    public ServerWebExchange getExchange() {
        return this.exchange;
    }

    /**
     * 设置HTTP请求-响应交互的契约.
     * @param exchange http请求-响应交互的契约
     */
    public void setExchange(ServerWebExchange exchange) {
        this.exchange = exchange;
        this.request = exchange.getRequest();
        this.response = exchange.getResponse();
    }

    /**
     * 执行过滤器.
     * @return 执行状态
     */
    public abstract Result doFilter();

    /**
     * 执行过滤器.
     * @param roles 角色
     * @return 是否继续执行
     */
    public Result doFilter(List<String> roles) {
        return doFilter();
    }

    /**
     * 获取登录类型.
     * @return 登录类型
     */
    public abstract int getFilterType();

    /**
     * 获取认证信息.
     * @param token token
     * @return 认证信息
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
     * 获取token.
     * @param request http servlet请求信息
     * @return token
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
     * 获取请求IP.
     * @return 请求IP
     */
    protected String getIp() {
        return request.getRemoteAddress().getAddress().getHostAddress();
    }

    /**
     * 获取角色列表.
     * @return 角色列表
     */
    public List<String> getRoles() {
        return this.roles;
    }

    /**
     * 设置角色列表.
     * @param roles 角色列表
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
