package com.quan.cloud.gateway.auth.filter.auth;


import com.quan.common.base.message.Result;
import com.quan.cloud.gateway.auth.config.AuthProperties;
import com.quan.cloud.gateway.auth.constant.AccessorTypeEnum;
import com.quan.cloud.gateway.auth.filter.AbstractAuthFilter;

/**
 * ip 白名单 过滤器
 *
 * @author wangquan
 * @date 2020/3/11 15:59
 */
public class IpFilter extends AbstractAuthFilter {

    private static AuthProperties authProperties;

    @Override
    public Result doFilter() {
        String addr = getIp();
        return allowIp(addr) ? Result.success() : Result.fail("您所在的国家或地区无法访问！");
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
