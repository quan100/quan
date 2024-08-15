package cn.javaquan.tools.chat.core.support;

import org.springframework.util.StringUtils;

/**
 * 抽象的授权处理器.
 *
 * @author javaquan
 * @since 1.0.0
 */
public class AbstractAuthorizationCheckProcessor implements AuthorizationProcessor {

    @Override
    public boolean checkAuth(String authorization) {
        return StringUtils.hasText(authorization);
    }

}
