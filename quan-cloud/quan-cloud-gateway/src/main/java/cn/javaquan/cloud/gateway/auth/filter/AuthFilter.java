package cn.javaquan.cloud.gateway.auth.filter;

import cn.javaquan.cloud.gateway.auth.service.QuanSecurityFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.cloud.gateway.auth.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 权限过滤器.
 *
 * @author wangquan
 * @since 2.3.1
 */
@RequiredArgsConstructor
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    /**
     * Order of Auth Filter.
     */
    public static final int AUTH_FILTER_ORDER = -10;

    private final QuanSecurityFeign quanSecurityFeign;

    private final AuthFilterFactory filterFactory;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String[] auths = filterFactory.authParse(request.getURI().getPath(), request.getMethodValue());
        if (null == auths) {
            return ResponseUtil.writeResponse(response, Result.fail("系统异常"));
        }
        List<AbstractAuthFilter> abstractAuthFilters = filterFactory.getFilter(auths[0]);
        List<String> roles = filterFactory.getRole(auths);

        for (AbstractAuthFilter abstractAuthFilter : abstractAuthFilters) {
            abstractAuthFilter.setExchange(exchange);
            abstractAuthFilter.setService(quanSecurityFeign);
            Result result = abstractAuthFilter.doFilter(roles);
            if (!result.isSuccess()) {
                response.setRawStatusCode(result.getCode());
                return ResponseUtil.writeResponse(response, result);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return AUTH_FILTER_ORDER;
    }

}
