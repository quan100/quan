package com.quan.app.mobile.bff.user.feign;

import com.quan.app.common.module.auth.AuthQuery;
import com.quan.app.common.module.system.UserPermissionTreeDTO;
import com.quan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author wangquan
 * @version 1.0.0
 * @date 2018-11-30 13:35:44
 */
@FeignClient(name = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url}")
public interface UserFeign {

    /**
     * 获取进入系统权限列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/user/account/userPermission")
    Result<List<UserPermissionTreeDTO>> getUserPermission(@SpringQueryMap AuthQuery query);

}
