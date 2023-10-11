package com.quan.cloud.gateway.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: wangquan
 * @date: 19-1-9 16:08
 * @description:
 */
@Data
@ConfigurationProperties(prefix = "quan.security.authorization")
public class AuthProperties {

    private LinkedList<String> auth;

    private List<String> ip = new ArrayList();

}
