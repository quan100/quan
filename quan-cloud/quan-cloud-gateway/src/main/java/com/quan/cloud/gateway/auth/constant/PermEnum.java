package com.quan.cloud.gateway.auth.constant;

import com.quan.cloud.gateway.auth.filter.AbstractAuthFilter;
import com.quan.cloud.gateway.auth.filter.auth.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 权限枚举
 *
 * @author wangquan
 */
@AllArgsConstructor
@Getter
public enum PermEnum {

    /**
     * 需登录访问（需验证token）
     */
    USER("user", new UserFilter()),

    /**
     * 任意访问（无token）
     */
    ANON("anon", new AnonymousFilter()),

    /**
     * 匿名访问（生成访客token）
     */
    NON_ANON("onym", new NonAnonymousFilter()),

    /**
     * 指定的IP可访问
     */
    IP("ip", new IpFilter()),

    /**
     * 禁止访问
     */
    STOP("stop", new StopFilter()),

    // 按钮权限
    QUERY("query", new PermissionsFilter()),
    GET("get", QUERY.filter),
    ADD("add", QUERY.filter),
    EDIT("edit", QUERY.filter),
    DELETE("delete", QUERY.filter),
    IMPORT("import", QUERY.filter),
    EXPORT("export", QUERY.filter),
    ADMIN("admin", QUERY.filter)
    // ./ 按钮权限
    ;

    /**
     * 权限类型
     * 配置基于该类型配置，当匹配到该类型时，才会执行对应的权限过滤器
     */
    String type;

    /**
     * 权限过滤器
     */
    AbstractAuthFilter filter;

    /**
     * 权限过滤器
     * 按照此枚举常量的序号顺序，基于ant匹配
     */
    public static Map<String, AbstractAuthFilter> filters = new LinkedHashMap<>(16);

    static {
        for (PermEnum value : PermEnum.values()) {
            filters.put(value.getType(), value.getFilter());
        }
    }
}
