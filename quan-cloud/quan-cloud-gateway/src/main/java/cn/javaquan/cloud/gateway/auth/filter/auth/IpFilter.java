package cn.javaquan.cloud.gateway.auth.filter.auth;

import cn.javaquan.cloud.gateway.auth.config.AuthProperties;
import cn.javaquan.cloud.gateway.auth.constant.AccessorTypeEnum;
import cn.javaquan.cloud.gateway.auth.constant.HttpStatusErrorEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.cloud.gateway.auth.filter.AbstractAuthFilter;

/**
 * ip 白名单 过滤器.
 *
 * @author wangquan
 * @since 2.3.1
 */
public class IpFilter extends AbstractAuthFilter {

    private static AuthProperties authProperties;

    @Override
    public Result doFilter() {
        String addr = getIp();
        return allowIp(addr) ? Result.success() : Result.fail(HttpStatusErrorEnum.IP_NOT_ALLOWED);
    }

    @Override
    public int getFilterType() {
        // 登录用户
        return AccessorTypeEnum.AUTHENTICATED.getType();
    }

    private boolean allowIp(String addr) {
        if (null == authProperties) {
            authProperties = getExchange().getApplicationContext().getBean(AuthProperties.class);
        }
        return authProperties.getIp().contains(addr);
    }

}
