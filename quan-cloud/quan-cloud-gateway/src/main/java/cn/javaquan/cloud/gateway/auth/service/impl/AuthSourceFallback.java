package cn.javaquan.cloud.gateway.auth.service.impl;

import cn.javaquan.cloud.gateway.auth.service.AuthSourceFeign;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限资源服务.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Component
public class AuthSourceFallback implements FallbackFactory<AuthSourceFeign> {

    @Override
    public AuthSourceFeign create(Throwable cause) {
        return new AuthSourceFeign() {
            @Override
            public Result<List<String>> getAuth(String defaultAuth) {
                cause.printStackTrace();
                return null;
            }
        };
    }

}
