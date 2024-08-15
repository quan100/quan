package cn.javaquan.app.service.system.convert;

import cn.javaquan.app.common.module.system.SysPermissionDTO;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.app.common.module.user.RolePermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统权限数据转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper
public interface PermissionAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    PermissionAssembler INSTANCE = Mappers.getMapper(PermissionAssembler.class);

    /**
     * 系统权限数据转换为角色权限数据.
     * @param dto 系统权限数据
     * @return 角色权限数据
     */
    @Mapping(target = "permissionId", source = "key")
    @Mapping(target = "action", source = "permission")
    RolePermissionDTO toRolePermissionDto(SysPermissionDTO dto);

    /**
     * 系统权限数据转换为角色权限数据.
     * @param dtos 系统权限数据
     * @return 角色权限数据
     */
    List<RolePermissionDTO> toRolePermissionDtoList(List<SysPermissionDTO> dtos);

    /**
     * 系统权限数据转换为用户权限树数据.
     * @param dto 系统权限数据
     * @return 用户权限数据
     */
    UserPermissionTreeDTO toUserPermissionTreeDTO(SysPermissionDTO dto);

}
