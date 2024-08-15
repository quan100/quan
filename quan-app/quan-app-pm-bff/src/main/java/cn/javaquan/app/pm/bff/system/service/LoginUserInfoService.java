package cn.javaquan.app.pm.bff.system.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.javaquan.app.common.module.user.RolePermissionDTO;
import cn.javaquan.app.common.module.user.UserInfoDTO;
import cn.javaquan.app.common.module.user.UserRoleDTO;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.security.common.dto.entity.AuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户信息表.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class LoginUserInfoService {

    /**
     * 获取当前登录用户信息.
     * @param authEntity 用户认证信息
     * @return 用户信息
     */
    public Result<UserInfoDTO> currentUser(AuthEntity authEntity) {
        UserInfoDTO userInfoDto = ((JSONObject) authEntity.getData()).toJavaObject(UserInfoDTO.class);
        List<UserRoleDTO> roleDtos = JSON.parseArray(JSON.toJSONString(authEntity.getRoles()), UserRoleDTO.class);

        List<String> paths = roleDtos.stream()
            .flatMap(userRoleDto -> userRoleDto.getPermissions().stream())
            .map(RolePermissionDTO::getPath)
            .filter(Objects::nonNull)
            .distinct()
            .map(path -> {
                String[] pathParams = path.split("\\?");
                return pathParams[0];
            })
            .collect(Collectors.toList());

        userInfoDto.setRoles(roleDtos);
        userInfoDto.setPaths(paths);
        return Result.success(userInfoDto);
    }

}
