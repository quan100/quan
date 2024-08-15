package cn.javaquan.cloud.gateway.auth.factory;

import cn.javaquan.cloud.gateway.auth.constant.PermEnum;
import cn.javaquan.cloud.gateway.auth.config.AuthProperties;
import cn.javaquan.cloud.gateway.auth.service.IAuthSource;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限责任链配置服务.
 * <p>
 * 产生责任链，确定每个url的访问权限
 *
 * @author wangquan
 * @since 1.0.0
 */
public class ChainDefinitionSource {

    /**
     * 静态资源访问权限.
     */
    @Autowired
    private AuthProperties authProperties;

    @Setter
    private IAuthSource authSource;

    private Map<String, String> sections = new LinkedHashMap();

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
     * 默认为键值形式配置的数据，通过{@link #AUTH_REGEX}分隔后，长度为{@link #AUTH_LENGTH}；若不等于该长度均为配置错误，则自动忽略错误配置
     */
    public static final int AUTH_LENGTH = 2;

    /**
     * 获取责任链配置数据.
     * <p>
     * 当配置 enabled 为 true 时，根据权限属性配置的信息，通过HTTP接口获取权限配置.
     * @param enabled 是否从远程获取权限配置
     * @return 责任链配置数据
     */
    public Map getFilterChain(boolean enabled) {
        this.sections.clear();
        if (enabled) {
            List<String> systemAuthSource = this.authSource.getAuth();
            load(systemAuthSource);
        }

        // 加载默认的url
        load(this.authProperties.getAuth());

        // 所有资源的访问权限，必须放在最后
        this.sections.put("/**", PermEnum.USER.getType());
        return this.sections;
    }

    private void load(List scanner) {
        if (scanner == null || scanner.size() == 0) {
            return;
        }
        for (Iterator<String> it = scanner.iterator(); it.hasNext();) {
            String auth = it.next();
            addSection(build(auth));
        }
    }

    private void addSection(String[] auths) {
        if (null != auths) {
            addSection(auths[0], auths[1]);
        }
    }

    private void addSection(String name, String contentString) {
        if (contentString.length() > 0) {
            String cleaned = StringUtils.trimToEmpty(contentString);
            name = StringUtils.trimToEmpty(name);
            if (cleaned != null) {
                if (StringUtils.isNotBlank(name)) {
                    this.sections.put(name, cleaned);
                }
            }
        }
    }

    private String[] build(String auth) {
        if (StringUtils.isNotBlank(auth)) {
            String[] auths = auth.replace(" ", "").split(AUTH_REGEX);
            if (auths.length == AUTH_LENGTH) {
                return auths;
            }
        }
        return null;
    }

}
