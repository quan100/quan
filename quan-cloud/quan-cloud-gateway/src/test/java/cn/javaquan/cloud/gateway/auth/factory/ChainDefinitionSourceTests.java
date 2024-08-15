package cn.javaquan.cloud.gateway.auth.factory;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;

class ChainDefinitionSourceTests {

    @Test
    void getFilterChain() {
    }

    @Test
    void authAnalyzeTest() {
        String auth = "/user/get/info = user, ip,";
        String[] auths = auth.replace(" ", "").split(ChainDefinitionSource.AUTH_REGEX);
        System.out.println(JSON.toJSONString(auths));

        String contentString = auths[1];
        String cleaned = StringUtils.trimToEmpty(contentString);

        System.out.println(cleaned);

        String[] authAndRoles = contentString.split(ChainDefinitionSource.ROLE_REGEX);
        contentString = authAndRoles[0];

        String[] filterKeys = contentString.split(",");
        System.out.println(JSON.toJSONString(filterKeys));
    }

}
