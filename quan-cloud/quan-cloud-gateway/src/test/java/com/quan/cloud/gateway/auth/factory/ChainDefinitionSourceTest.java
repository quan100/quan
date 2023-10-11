package com.quan.cloud.gateway.auth.factory;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;

import static com.quan.cloud.gateway.auth.factory.ChainDefinitionSource.*;

class ChainDefinitionSourceTest {

    @Test
    void getFilterChain() {
    }

    public static void main(String[] args) {
        String auth = "/user/get/info = user, ip,";
        String[] auths = auth.replace(" ", "").split(AUTH_REGEX);
        System.out.println(JSON.toJSONString(auths));

        String contentString = auths[1];
        String cleaned = StringUtils.trimToEmpty(contentString);

        System.out.println(cleaned);

        String[] authAndRoles = contentString.split(ROLE_REGEX);
        contentString = authAndRoles[0];

        String[] filterKeys = contentString.split(",");
        System.out.println(JSON.toJSONString(filterKeys));

        System.out.println(JSON.toJSONString(authAndRoles[1].split(",")));

        System.out.println("test -");
        System.out.println(authAndRoles[3]);
    }
}