package cn.javaquan.security.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * token配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "quan.token")
public class TokenConfiguration {

    /**
     * 签发者.
     */
    private String issuer;

    /**
     * 密钥.
     * <p>
     * 用于对JWT进行数字签名的base64编码的特定于算法的签名密钥.
     */
    private String key;

    /**
     * 主题.
     * <p>
     * 设置JWT声明子(主题)值.
     */
    private String subject;

    /**
     * 声明名称.
     * <p>
     * JWT声明属性名称
     */
    private String claimsName;

}
