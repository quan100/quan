package com.quan.app.pm.bff.system.service;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.quan.app.common.module.user.RolePermissionDTO;
import com.quan.app.common.module.user.UserInfoDTO;
import com.quan.app.common.module.user.UserRoleDTO;
import com.quan.common.base.message.Result;
import com.quan.security.common.dto.entity.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户信息表
 *
 * @author wangquan
 * @version 1.0.0
 * @date 2020-08-24 23:47:27
 */
@RequiredArgsConstructor
@Component
public class LoginUserInfoService {

    public Result<UserInfoDTO> currentUser(AuthEntity authEntity) {
        UserInfoDTO userInfoDto = ((JSONObject) authEntity.getData()).toJavaObject(UserInfoDTO.class);
        List<UserRoleDTO> roleDtos = JSONUtil.toList(JSON.toJSONString(authEntity.getRoles()), UserRoleDTO.class);

        List<String> paths = roleDtos.stream()
                .flatMap(userRoleDto -> userRoleDto.getPermissions().stream())
                .map(RolePermissionDTO::getPath).filter(Objects::nonNull).distinct()
                .map(path -> {
                    String[] pathParams = path.split("\\?");
                    return pathParams[0];
                }).collect(Collectors.toList());

        userInfoDto.setRoles(roleDtos);
        userInfoDto.setPaths(paths);
        return Result.success(userInfoDto);
    }

}

