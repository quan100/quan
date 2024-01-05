package cn.javaquan.cloud.gateway.auth.service;

import cn.javaquan.common.base.message.Result;
import cn.javaquan.cloud.gateway.auth.service.impl.AuthSourceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wangquan
 * @date 2020/3/11 16:24
 */
@FeignClient(name = "${quan.app.auth-source.name:default-auth-source}", url = "${quan.app.auth-source.url:}", fallbackFactory = AuthSourceFallback.class)
public interface AuthSourceFeign {

    /**
     * 获取权限资源
     *
     * @param defaultAuth 默认权限
     * @return
     */
    @GetMapping(value = "${quan.app.auth-source.path}")
    Result<List<String>> getAuth(@RequestParam String defaultAuth);
}
