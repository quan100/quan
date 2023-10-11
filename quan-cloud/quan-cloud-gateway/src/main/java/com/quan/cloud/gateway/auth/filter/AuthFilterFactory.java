package com.quan.cloud.gateway.auth.filter;

import com.quan.cloud.gateway.auth.factory.ChainDefinitionSource;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;
import java.util.stream.Collectors;

import static com.quan.cloud.gateway.auth.factory.ChainDefinitionSource.ROLE_REGEX;

/**
 * @author wangquan
 * @date 2020/3/11 15:44
 */
@Setter
public class AuthFilterFactory {

    private ChainDefinitionSource chainDefinitionSource;

    /**
     * 权限过滤器
     */
    private Map<String, AbstractAuthFilter> filters = new LinkedHashMap();

    /**
     * filterChainMap
     */
    private Map<String, String> filterChainMap;

    public AuthFilterFactory(ChainDefinitionSource chainDefinitionSource) {
        this.chainDefinitionSource = chainDefinitionSource;
    }

    /**
     * 权限解析
     *
     * @param url
     * @return
     */
    public String[] authParse(String url) {
        String auth = null;

        PathMatcher matcher = new AntPathMatcher();
        for (Map.Entry<String, String> entry : filterChainMap.entrySet()) {
            if (matcher.match(entry.getKey(), url)) {
                auth = entry.getValue();
                break;
            }
        }

        if (StringUtils.isEmpty(auth)) {
            return null;
        }
        String[] authAndRole = auth.split(ROLE_REGEX);
        return authAndRole;
    }

    /**
     * 获取filter
     *
     * @param authKey
     * @return
     */
    public List<AbstractAuthFilter> getFilter(String authKey) {
        List<AbstractAuthFilter> authFilters = new LinkedList();
        String[] filterKeys = authKey.split(",");
        for (String key : filterKeys) {
            authFilters.add(filters.get(key));
        }
        return authFilters;
    }

    /**
     * 获取Role
     *
     * @param auths 权限解析数据
     *              index0：权限
     *              index1：角色
     * @return
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

    public void setFilterChainMap(boolean enabled) {
        this.filterChainMap = chainDefinitionSource.getFilterChain(enabled);
    }
}
