package cn.javaquan.app.service.system.feign;

import cn.javaquan.security.common.dto.entity.AuthEntity;
import cn.javaquan.security.common.dto.request.AuthenticateRequest;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangquan
 */
@FeignClient(name = "${quan.app.feign.security.name}", url = "${quan.app.feign.security.url:}")
public interface AuthFeign {

    /**
     * token验证
     *
     * @param request
     * @return
     */
    @PostMapping("/auth/validate")
    AuthenticateResponse validate(@RequestBody @Validated AuthenticateRequest request);

    /**
     * token获取
     *
     * @param auth
     * @return token
     */
    @PostMapping("/auth/token")
    String token(@RequestBody @Validated AuthEntity auth);

    /**
     * 清除token信息
     *
     * @param token
     * @return
     */
    @GetMapping("/auth/clean")
    void clean(@RequestParam(value = "token") String token);
}
