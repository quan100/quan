package cn.javaquan.app.mobile.bff.user.feign;

import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 用户服务 Feign 接口.
 *
 * @author wangquan
 * @since 1.0.0
 */
@FeignClient(name = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url}")
public interface UserFeign {

    /**
     * 获取进入系统权限列表.
     * @param query 查询参数
     * @return 权限列表
     */
    @GetMapping("/service/sys/user/account/userPermission")
    Result<List<UserPermissionTreeDTO>> getUserPermission(@SpringQueryMap AuthQuery query);

}
