package com.quan.app.pm.bff.system.controller;

import com.quan.app.common.module.user.UserInfoDTO;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.service.LoginUserInfoService;
import com.quan.security.common.annotation.AuthUser;
import com.quan.security.common.dto.entity.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangquan
 * @version 1.0.0
 * @date 2020-02-12 19:50:38
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/info/")
public class SysLoginUserInfoController {

    private final LoginUserInfoService userInfoService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "currentUser")
    public Result<UserInfoDTO> currentUser(@AuthUser AuthEntity authEntity) {
        return userInfoService.currentUser(authEntity);
    }

}
