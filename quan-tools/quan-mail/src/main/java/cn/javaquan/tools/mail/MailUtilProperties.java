package cn.javaquan.tools.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * Configuration properties for mail support.
 *
 * @author javaquan
 * @since 2.2.0
 */
@ConfigurationProperties(prefix = "quan.mail")
public class MailUtilProperties {

    private final static String DEFAULT_ALIAS_NAME = "时光日记";

    private String aliasName;

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String determineDefaultAliasName() {
        if (StringUtils.hasText(this.aliasName)) {
            return this.aliasName;
        }
        return DEFAULT_ALIAS_NAME;
    }

}
