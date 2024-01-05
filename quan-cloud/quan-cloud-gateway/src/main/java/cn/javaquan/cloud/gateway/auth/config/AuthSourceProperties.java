package cn.javaquan.cloud.gateway.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangquan
 */
@Data
@ConfigurationProperties(prefix = "quan.app.auth-source")
public class AuthSourceProperties {

    private boolean enabled = false;
    private String name = "default-auth-source";
    private String url;
    private String path;

}
