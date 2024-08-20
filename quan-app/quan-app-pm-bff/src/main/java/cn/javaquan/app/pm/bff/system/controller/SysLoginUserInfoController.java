package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.app.common.module.user.UserInfoDTO;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.pm.bff.system.service.LoginUserInfoService;
import cn.javaquan.security.common.annotation.AuthUser;
import cn.javaquan.security.common.dto.entity.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户信息管理接口.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/info/")
public class SysLoginUserInfoController {

    private final LoginUserInfoService userInfoService;

    /**
     * 获取用户信息.
     * @param authEntity 用户认证信息
     * @return 用户信息
     */
    @ResponseBody
    @GetMapping("/currentUser")
    public Result<UserInfoDTO> currentUser(@AuthUser AuthEntity authEntity) {
        return userInfoService.currentUser(authEntity);
    }

}
