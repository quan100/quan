package com.quan.app.service.system.convert;

import com.quan.app.common.module.system.SysPermissionDTO;
import com.quan.app.common.module.system.UserPermissionTreeDTO;
import com.quan.app.common.module.user.RolePermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wangquan
 */
@Mapper
public interface PermissionAssembler {

    PermissionAssembler INSTANCE = Mappers.getMapper(PermissionAssembler.class);

    @Mapping(target = "permissionId", source = "key")
    @Mapping(target = "action", source = "permission")
    RolePermissionDTO toRolePermissionDto(SysPermissionDTO dto);

    List<RolePermissionDTO> toRolePermissionDtoList(List<SysPermissionDTO> dtos);

    UserPermissionTreeDTO toUserPermissionTreeDTO(SysPermissionDTO dto);
}
