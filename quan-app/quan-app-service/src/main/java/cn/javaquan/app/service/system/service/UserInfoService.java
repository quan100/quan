package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.module.system.SysRoleDTO;
import cn.javaquan.app.common.module.user.UserRoleDTO;
import cn.javaquan.app.common.util.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
public class UserInfoService {

    private final PermissionService permissionService;


//    public UserInfoDTO getInfo(AuthEntity authEntity) {
//        UserInfoDTO userInfoDto = ((JSONObject) authEntity.getData()).toJavaObject(UserInfoDTO.class);
//        List<UserRoleDTO> roleDtos = JSONUtil.toList(JSON.toJSONString(authEntity.getRoles()), UserRoleDTO.class);
//
//        Map<String, RolePermissionDTO> rolePerm = roleDtos.stream().flatMap(userRoleDto -> userRoleDto.getPermissions().stream())
//                .collect(Collectors.toMap(RolePermissionDTO::getPermissionId, dto -> dto, (d1, d2) -> d1));
//
//        userInfoDto.setRoles(roleDtos);
//        userInfoDto.setRolePerm(rolePerm);
//        return userInfoDto;
//    }

    public List<UserRoleDTO> getUserRole(String userId) {
        List<SysRoleDTO> sysRoleDTOS = permissionService.queryUserRole(userId);
        if (Validate.isEmpty(sysRoleDTOS)) {
            return Collections.emptyList();
        }

        List<UserRoleDTO> sysRoleDtos = sysRoleDTOS.stream().map(rolePo -> {
            UserRoleDTO userRoleDto = new UserRoleDTO();
            userRoleDto.setCode(rolePo.getCode());
            userRoleDto.setName(rolePo.getName());
            userRoleDto.setPermissions(permissionService.queryRolePermission(null, Arrays.asList(rolePo.getId())));
            return userRoleDto;
        }).collect(Collectors.toList());
        return sysRoleDtos;
    }

}

