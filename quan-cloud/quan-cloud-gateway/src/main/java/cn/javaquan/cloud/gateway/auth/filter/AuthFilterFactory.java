package cn.javaquan.cloud.gateway.auth.filter;

import cn.javaquan.cloud.gateway.auth.constant.PermEnum;
import cn.javaquan.cloud.gateway.auth.factory.ChainDefinitionSource;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 权限处理工厂.
 *
 * @author wangquan
 * @since 2.3.1
 */
@Setter
public class AuthFilterFactory {

    /**
     * 权限责任链配置服务.
     */
    private ChainDefinitionSource chainDefinitionSource;

    /**
     * 权限过滤器.
     */
    private Map<String, PermEnum> filters = new LinkedHashMap<>();

    /**
     * 权限责任链配置数据.
     */
    private Map<String, String> filterChainMap;

    /**
     * 权限责任链配置数据. 根据请求方法配置的二级责任链.
     */
    private Map<String, Map<String, String>> methodFilterChain;

    /**
     * 设置权限责任链配置服务.
     * @param chainDefinitionSource 权限责任链配置服务
     */
    public AuthFilterFactory(ChainDefinitionSource chainDefinitionSource) {
        this.chainDefinitionSource = chainDefinitionSource;
    }

    /**
     * 权限解析.
     * <p>
     * 权限解析数据 index0：权限 index1：角色
     * @param url 请求url
     * @param method 请求方法
     * @return 权限解析数据
     */
    public String[] authParse(String url, String method) {
        PathMatcher matcher = new AntPathMatcher();
        // 关联方法的责任链解析
        for (Map.Entry<String, Map<String, String>> entry : this.methodFilterChain.entrySet()) {
            if (matcher.match(entry.getKey(), url)) {
                String auth = entry.getValue().get(method);
                if (!StringUtils.isEmpty(auth)) {
                    return auth.split(ChainDefinitionSource.ROLE_REGEX);
                }
            }
        }
        // 责任链解析，所有资源的访问权限，必须放在最后
        for (Map.Entry<String, String> entry : this.filterChainMap.entrySet()) {
            if (matcher.match(entry.getKey(), url)) {
                String auth = entry.getValue();
                if (!StringUtils.isEmpty(auth)) {
                    return auth.split(ChainDefinitionSource.ROLE_REGEX);
                }
            }
        }
        return null;
    }

    /**
     * 获取权限过滤器.
     * @param authKey 权限
     * @return 权限处理器
     */
    public List<AbstractAuthFilter> getFilter(String authKey) {
        List<AbstractAuthFilter> authFilters = new LinkedList<>();
        String[] filterKeys = authKey.split(",");
        for (String key : filterKeys) {
            authFilters.add(this.filters.get(key).getFilter());
        }
        return authFilters;
    }

    /**
     * 获取权限对应的角色列表.
     * @param auths 权限解析数据 index0：权限 index1：角色
     * @return 角色列表
     */
    public List<String> getRole(String[] auths) {
        if (auths.length < ChainDefinitionSource.AUTH_LENGTH) {
            return null;
        }
        String roleKey = auths[1];
        List<String> roles = null;
        if (StringUtils.isNotBlank(roleKey)) {
            roles = Arrays.stream(roleKey.split(",")).collect(Collectors.toList());
        }
        return roles;
    }

    /**
     * 设置权限责任链配置数据.
     * <p>
     * 当配置 enabled 为 true 时，根据权限属性配置的信息，通过HTTP接口获取权限配置.
     * @param enabled 是否从远程获取权限配置
     */
    public void setFilterChainMap(boolean enabled) {
        this.filterChainMap = chainDefinitionSource.getFilterChain(enabled);
        this.methodFilterChain = chainDefinitionSource.getMethodFilterChain();
    }

}
