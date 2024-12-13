package cn.javaquan.cloud.gateway.auth.constant;

import cn.javaquan.cloud.gateway.auth.filter.AbstractAuthFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.AnonymousFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.IpFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.NonAnonymousFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.PermissionsFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.StopFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.UserFilter;
import cn.javaquan.cloud.gateway.auth.filter.auth.AdminFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限枚举.
 * <p>
 * 确定每一个权限的处理器设置
 *
 * @author wangquan
 * @since 2.3.1
 */
@AllArgsConstructor
@Getter
public enum PermEnum {

    /**
     * 需登录访问（需验证token）.
     */
    USER("user", null, new UserFilter()),

    /**
     * 任意访问（无token）.
     */
    ANON("anon", null, new AnonymousFilter()),

    /**
     * 匿名访问（生成访客token）.
     */
    NON_ANON("onym", null, new NonAnonymousFilter()),

    /**
     * 指定的IP可访问.
     */
    IP("ip", null, new IpFilter()),

    /**
     * 禁止访问.
     */
    STOP("stop", null, new StopFilter()),

    /**
     * 系统权限. 一般为系统配置的所有 url 权限
     */
    PERMISSIONS("permissions", null, new PermissionsFilter()),

    // 按钮权限
    /**
     * 查询权限.
     */
    QUERY("query", null, PERMISSIONS.filter),
    /**
     * 获取权限.
     */
    GET("get", HttpMethod.GET.name(), PERMISSIONS.filter),
    /**
     * 新增权限.
     */
    ADD("add", HttpMethod.POST.name(), PERMISSIONS.filter),

    /**
     * 修改权限.
     */
    EDIT("edit", HttpMethod.PUT.name(), PERMISSIONS.filter),
    /**
     * 部分修改权限.
     */
    PARTIAL_EDIT("partial-edit", HttpMethod.PATCH.name(), PERMISSIONS.filter),
    /**
     * 删除权限.
     */
    DELETE("delete", HttpMethod.DELETE.name(), PERMISSIONS.filter),
    /**
     * 导入权限.
     */
    IMPORT("import", null, PERMISSIONS.filter),

    /**
     * 导出权限.
     */
    EXPORT("export", null, IMPORT.filter),
    /**
     * 管理员权限.
     */
    ADMIN("admin", null, new AdminFilter());
    // ./ 按钮权限

    /**
     * 权限类型. 配置基于该类型配置，当匹配到该类型时，才会执行对应的权限过滤器
     */
    final String type;

    /**
     * 定义设置当前权限的请求方式. 默认为空，当为空时，不限制请求方式.
     * <p>
     * 若 URI 配置相同，但配置的权限不同，则通过请求方式寻找二级权限过滤器. <pre>
     * 这种设计模式主要是为了解决 rest api 的场景，例如：
     *  {POST}/api/user
     *  {GET}/api/user
     * </pre>
     */
    final String method;

    /**
     * 权限过滤器.
     */
    final AbstractAuthFilter filter;

    /**
     * 权限过滤器. 按照此枚举常量的序号顺序，基于ant匹配
     */
    public static final Map<String, PermEnum> MAPPINGS = new HashMap<>(16);

    public static PermEnum resolve(String perm) {
        return (perm != null) ? MAPPINGS.get(perm) : null;
    }

    static {
        for (PermEnum value : PermEnum.values()) {
            MAPPINGS.put(value.getType(), value);
        }
    }

}
