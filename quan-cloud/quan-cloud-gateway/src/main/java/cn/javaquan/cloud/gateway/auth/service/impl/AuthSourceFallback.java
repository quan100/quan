package cn.javaquan.cloud.gateway.auth.service.impl;

import cn.javaquan.cloud.gateway.auth.service.AuthSourceFeign;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangquan
 * @date 2020/3/11 18:33
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
