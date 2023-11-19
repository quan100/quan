package cn.javaquan.tools.chat.core.support;

import org.springframework.util.StringUtils;

public class AbstractAuthorizationCheckProcessor implements AuthorizationProcessor {

    @Override
    public boolean checkAuth(String authorization) {
        return StringUtils.hasText(authorization);
    }

}
