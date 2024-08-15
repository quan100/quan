package cn.javaquan.cloud.gateway.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 用户认证配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "quan.security.authorization")
public class AuthProperties {

    /**
     * 权限配置.
     */
    private LinkedList<String> auth;

    /**
     * IP白名单.
     */
    private List<String> ip = new ArrayList<>();

}
