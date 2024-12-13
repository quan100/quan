package cn.javaquan.cloud.gateway.auth.factory;

import cn.javaquan.cloud.gateway.auth.constant.PermEnum;
import cn.javaquan.cloud.gateway.auth.config.AuthProperties;
import cn.javaquan.cloud.gateway.auth.service.IAuthSource;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限责任链配置服务.
 * <p>
 * 产生责任链，确定每个url的访问权限
 *
 * @author wangquan
 * @since 2.3.1
 */
@Slf4j
public class ChainDefinitionSource {

    /**
     * 静态资源访问权限.
     */
    @Autowired
    private AuthProperties authProperties;

    @Setter
    private IAuthSource authSource;

    private final Map<String, String> sections = new LinkedHashMap<>();

    private final Map<String, Map<String, String>> methodSections = new LinkedHashMap<>();

    /**
     * 权限配置分隔符.
     */
    public static final String AUTH_REGEX = "=";

    /**
     * 角色配置分隔符.
     */
    public static final String ROLE_REGEX = "&";

    /**
     * 分隔后的长度.
     * 默认为键值形式配置的数据，通过{@link #AUTH_REGEX}分隔后，长度为{@code AUTH_LENGTH}；若不等于该长度均为配置错误，则自动忽略错误配置
     */
    public static final int AUTH_LENGTH = 2;

    /**
     * 获取责任链配置数据.
     * <p>
     * 当配置 enabled 为 true 时，根据权限属性配置的信息，通过HTTP接口获取权限配置.
     * @param enabled 是否从远程获取权限配置
     * @return 责任链配置数据
     */
    public Map<String, String> getFilterChain(boolean enabled) {
        this.sections.clear();
        this.methodSections.clear();
        List<String> systemAuthSource = null;
        if (enabled) {
            systemAuthSource = this.authSource.getAuth();
            load(systemAuthSource);
        }

        // 加载默认的url
        load(this.authProperties.getAuth());

        // 如果从远程获取权限配置失败，则不添加所有资源的访问权限
        if (enabled && CollectionUtils.isEmpty(systemAuthSource)) {
            log.warn("从远程获取权限配置失败，请检查配置信息是否正确");
            return this.sections;
        }
        // 所有资源的访问权限，必须放在最后
        this.sections.put("/**", PermEnum.USER.getType());
        return this.sections;
    }

    /**
     * 获取责任链配置数据.
     * <p>
     * 根据请求方法配置的二级责任链.
     * @return 责任链配置数据
     */
    public Map<String, Map<String, String>> getMethodFilterChain() {
        return this.methodSections;
    }

    private void load(List<String> scanner) {
        if (CollectionUtils.isEmpty(scanner)) {
            return;
        }
        for (String auth : scanner) {
            addSection(build(auth));
        }
    }

    private void addSection(String[] auths) {
        if (null != auths) {
            addSection(auths[0], auths[1]);
        }
    }

    private void addSection(String uri, String authInfo) {
        uri = StringUtils.trimToEmpty(uri);
        authInfo = StringUtils.trimToEmpty(authInfo);
        if (StringUtils.isBlank(uri) || StringUtils.isBlank(authInfo)) {
            return;
        }
        if (this.methodSections.containsKey(uri)) {
            addMethodSection(uri, authInfo);
            return;
        }
        if (this.sections.containsKey(uri)) {
            addMethodSection(uri, this.sections.remove(uri));
            addMethodSection(uri, authInfo);
            return;
        }
        this.sections.put(uri, authInfo);
    }

    private void addMethodSection(String uri, String authInfo) {
        // 获取权限关联的 method。index-0：权限 index-1：角色
        String[] methodParts = authInfo.split(ChainDefinitionSource.ROLE_REGEX);
        PermEnum permEnum = PermEnum.resolve(methodParts[0]);
        if (permEnum == null || permEnum.getMethod() == null) {
            return;
        }
        Map<String, String> methodSection = this.methodSections.computeIfAbsent(uri, k -> new LinkedHashMap<>());
        methodSection.put(permEnum.getMethod(), authInfo);
    }

    private String[] build(String auth) {
        if (StringUtils.isBlank(auth)) {
            return null;
        }
        String[] auths = auth.replace(" ", "").split(AUTH_REGEX);
        if (auths.length == AUTH_LENGTH) {
            return auths;
        }
        return null;
    }

}
