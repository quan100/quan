package cn.javaquan.cloud.gateway.auth.constant;

import cn.javaquan.cloud.gateway.auth.filter.AbstractAuthFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.AnonymousFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.IpFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.NonAnonymousFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.PermissionsFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.StopFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.UserFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 权限枚举.
 * <p>
 * 确定每一个权限的处理器设置
 *
 * @author wangquan
 * @since 1.0.0
 */
@AllArgsConstructor
@Getter
public enum PermEnum {

    /**
     * 需登录访问（需验证token）.
     */
    USER("user", new UserFilter()),

    /**
     * 任意访问（无token）.
     */
    ANON("anon", new AnonymousFilter()),

    /**
     * 匿名访问（生成访客token）.
     */
    NON_ANON("onym", new NonAnonymousFilter()),

    /**
     * 指定的IP可访问.
     */
    IP("ip", new IpFilter()),

    /**
     * 禁止访问.
     */
    STOP("stop", new StopFilter()),

    // 按钮权限
    /**
     * 查询权限.
     */
    QUERY("query", new PermissionsFilter()),
    /**
     * 获取权限.
     */
    GET("get", QUERY.filter),
    /**
     * 新增权限.
     */
    ADD("add", QUERY.filter),

    /**
     * 修改权限.
     */
    EDIT("edit", QUERY.filter),
    /**
     * 删除权限.
     */
    DELETE("delete", QUERY.filter),
    /**
     * 导入权限.
     */
    IMPORT("import", QUERY.filter),

    /**
     * 导出权限.
     */
    EXPORT("export", QUERY.filter),
    /**
     * 管理员权限.
     */
    ADMIN("admin", QUERY.filter);
    // ./ 按钮权限

    /**
     * 权限类型. 配置基于该类型配置，当匹配到该类型时，才会执行对应的权限过滤器
     */
    final String type;

    /**
     * 权限过滤器.
     */
    final AbstractAuthFilter filter;

    /**
     * 权限过滤器. 按照此枚举常量的序号顺序，基于ant匹配
     */
    public static Map<String, AbstractAuthFilter> filters = new LinkedHashMap<>(16);

    static {
        for (PermEnum value : PermEnum.values()) {
            filters.put(value.getType(), value.getFilter());
        }
    }

}
