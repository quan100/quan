package cn.javaquan.cloud.gateway.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 权限资源配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "quan.app.auth-source")
public class AuthSourceProperties {

    private boolean enabled = false;

    private String name = "default-auth-source";

    private String url;

    private String path;

}
