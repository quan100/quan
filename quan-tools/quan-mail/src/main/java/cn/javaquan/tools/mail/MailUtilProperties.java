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

    private static final String DEFAULT_ALIAS_NAME = "时光日记";

    /**
     * 邮件发送者别名.
     */
    private String aliasName;

    /**
     * 获取邮件发送者别名.
     * @return 邮件发送者别名.
     */
    public String getAliasName() {
        return this.aliasName;
    }

    /**
     * 设置邮件发送者别名.
     * @param aliasName 邮件发送者别名.
     */
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    /**
     * 确定默认的邮件发送者别名.
     * @return 默认的邮件发送者别名.
     */
    public String determineDefaultAliasName() {
        if (StringUtils.hasText(this.aliasName)) {
            return this.aliasName;
        }
        return DEFAULT_ALIAS_NAME;
    }

}
