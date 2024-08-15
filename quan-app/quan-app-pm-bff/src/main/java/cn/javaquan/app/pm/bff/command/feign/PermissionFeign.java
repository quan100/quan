package cn.javaquan.app.pm.bff.command.feign;

import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.pm.bff.command.feign.fallback.PermissionFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 用户账号.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = PermissionFallback.class)
public interface PermissionFeign {

    /**
     * 获取用户权限.
     * @param query 查询参数
     * @return 权限列表
     */
    @GetMapping("/service/sys/user/account/userPermission")
    Result<List<UserPermissionTreeDTO>> getUserPermission(@SpringQueryMap AuthQuery query);

}
