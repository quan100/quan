package cn.javaquan.app.mobile.bff.user.controller;

import cn.javaquan.app.mobile.bff.user.feign.UserFeign;
import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.security.common.annotation.AuthUser;
import cn.javaquan.security.common.dto.entity.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author wangquan
 * @version 1.0.0
 * @date 2020-02-12 19:50:38
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/permission/")
public class UserPermissionController {

    private final UserFeign userFeign;

    /**
     * 获取用户菜单列表
     *
     * @return
     */
    @GetMapping(value = "nav")
    public Result<List<UserPermissionTreeDTO>> getInfo(@AuthUser(check = false) AuthEntity authEntity) {
        AuthQuery query = new AuthQuery();
        query.setAppType(authEntity.getAppType());
        return userFeign.getUserPermission(query);
    }

}
