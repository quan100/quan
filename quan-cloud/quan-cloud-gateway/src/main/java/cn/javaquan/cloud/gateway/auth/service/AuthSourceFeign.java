package cn.javaquan.cloud.gateway.auth.service;

import cn.javaquan.common.base.message.Result;
import cn.javaquan.cloud.gateway.auth.service.impl.AuthSourceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 权限资源服务.
 *
 * @author wangquan
 * @since 1.0.0
 */
@FeignClient(name = "${quan.app.auth-source.name:default-auth-source}", url = "${quan.app.auth-source.url:}",
        fallbackFactory = AuthSourceFallback.class)
public interface AuthSourceFeign {

    /**
     * 获取权限资源.
     * @param defaultAuth 默认权限
     * @return 权限资源
     */
    @GetMapping("${quan.app.auth-source.path}")
    Result<List<String>> getAuth(@RequestParam String defaultAuth);

}
