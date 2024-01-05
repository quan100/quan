package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.constant.RoleTypeEnum;
import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.app.common.module.system.*;
import cn.javaquan.app.common.module.system.convert.SystemAssembler;
import cn.javaquan.app.common.module.user.RolePermissionDTO;
import cn.javaquan.app.common.util.ArraysUtil;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.common.util.tree.TreeUtil;
import cn.javaquan.app.service.system.convert.PermissionAssembler;
import cn.javaquan.app.service.system.feign.SysPermissionRepositoryFeign;
import cn.javaquan.app.service.system.feign.SysRolePermissionRepositoryFeign;
import cn.javaquan.app.service.system.feign.SysRoleRepositoryFeign;
import cn.javaquan.app.service.system.feign.SysUserRoleRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 访问系统获取权限处理服务
 *
 * @author wangquan
 */
@RequiredArgsConstructor
@Component
public class PermissionService {

    private final SysRoleRepositoryFeign sysRoleRepository;
    private final SysRolePermissionRepositoryFeign sysRolePermissionRepository;
    private final SysPermissionRepositoryFeign sysPermissionRepository;
    private final SysUserRoleRepositoryFeign sysUserRoleRepository;

    /**
     * 获取进入系统权限列表
     *
     * @param query
     * @return
     */
    public List<UserPermissionTreeDTO> getUserPermission(AuthQuery query) {
        if (query.isLogin()) {
            return getLoginPermission(query);
        }
        return getAnonymousPermission(query);
    }

    /**
     * 获取匿名用户权限列表
     * 匿名用户只允许获取开放角色权限
     *
     * @param query
     * @return
     */
    public List<UserPermissionTreeDTO> getAnonymousPermission(AuthQuery query) {
        // 获取开放角色
        Result<SysRoleDTO> result = sysRoleRepository.getRole(SystemAssembler.INSTANCE.toSysRoleQuery(RoleTypeEnum.OPEN.getCode(), query.getAppType(), 0));
        if (!result.isData()) {
            return Collections.emptyList();
        }
        return queryRoleMenuPermission(Arrays.asList(result.getData().getId()));
    }

    /**
     * 获取登录用户权限列表
     *
     * @param query
     * @return
     */
    public List<UserPermissionTreeDTO> getLoginPermission(AuthQuery query) {
        List<Long> permRoleIds = queryUserRoleId(query.getAuthId());
        return queryRoleMenuPermission(permRoleIds);
    }

    /**
     * 获取用户角色
     *
     * @param userId
     * @return
     */
    public List<SysRoleDTO> queryUserRole(String userId) {
        List<Long> roleIds = this.queryUserRoleId(userId);
        if (Validate.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        Result<List<SysRoleDTO>> result = sysRoleRepository.getRoles(SystemAssembler.INSTANCE.toSysRoleQuery(roleIds));
        if (result.isSuccess()) {
            return result.getData();
        }
        return Collections.emptyList();
    }

    /**
     * 获取登录用户角色ID
     *
     * @param userId
     * @return
     */
    private List<Long> queryUserRoleId(String userId) {
        Result<List<SysUserRoleDTO>> result = sysUserRoleRepository.getUserRole(userId);
        if (!result.isSuccess()) {
            return Collections.emptyList();
        }
        List<SysUserRoleDTO> sysUserRoleDTOS = result.getData();

        // 获取用户角色
        List<Long> roleIds = sysUserRoleDTOS.stream().map(SysUserRoleDTO::getRoleId).collect(Collectors.toList());
        // 获取角色
        Result<List<SysRoleDTO>> sysRolePos = sysRoleRepository.getRoles(SystemAssembler.INSTANCE.toSysRoleQuery(roleIds));
        List<Long> sysRolePoIds;
        if (!sysRolePos.isData()) {
            return Collections.emptyList();
        } else {
            sysRolePoIds = sysRolePos.getData().stream().filter(sysRolePo -> sysRolePo.getStatus() == 0 && !sysRolePo.getDelFlag()).map(SysRoleDTO::getId).collect(Collectors.toList());
        }

        // 取交集，过滤无效角色
        List<Long> userRoleIds = roleIds.stream().filter(sysRolePoIds::contains).collect(Collectors.toList());
        if (Validate.isEmpty(userRoleIds)) {
            return Collections.emptyList();
        }
        return userRoleIds;
    }

    /**
     * 获取角色菜单权限
     *
     * @param roleIds 角色ID列表
     * @return
     */
    private List<UserPermissionTreeDTO> queryRoleMenuPermission(List<Long> roleIds) {
        if (Validate.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        Result<List<SysPermissionDTO>> result = sysPermissionRepository.getRolePermission(SystemAssembler.INSTANCE.toSysRolePermissionQuery(null, roleIds));
        if (!result.isData()) {
            return Collections.emptyList();
        }
        List<SysPermissionDTO> sysPermissionDTOS = result.getData();

        Map<Integer, List<SysPermissionDTO>> sysPermissionPoTypeGroup = sysPermissionDTOS.stream().collect(Collectors.groupingBy(SysPermissionDTO::getType));
        List<SysPermissionDTO> menuPerms = ArraysUtil.merge(sysPermissionPoTypeGroup.get(1), sysPermissionPoTypeGroup.get(0));

        if (Validate.isEmpty(menuPerms)) {
            return Collections.emptyList();
        }

        Map<Long, List<SysPermissionDTO>> permGroup;
        if (sysPermissionPoTypeGroup.containsKey(2)) {
            List<SysPermissionDTO> permList = sysPermissionPoTypeGroup.get(2);
            permGroup = permList.stream().collect(Collectors.groupingBy(SysPermissionDTO::getParentId));
        } else {
            permGroup = new HashMap<>();
        }

        // 过滤按钮权限
        List<UserPermissionTreeDTO> userPermissionDtos = menuPerms.stream().map(sysPermissionPo -> {
            UserPermissionTreeDTO userPermissionDto = PermissionAssembler.INSTANCE.toUserPermissionTreeDTO(sysPermissionPo);

            userPermissionDto.setPermissions(PermissionAssembler.INSTANCE.toRolePermissionDtoList(permGroup.get(sysPermissionPo.getId())));
            return userPermissionDto;
        }).sorted(Comparator.comparing(UserPermissionDTO::getSort)).collect(Collectors.toList());
        return TreeUtil.asTreeNodes(userPermissionDtos);
    }

    /**
     * 获取角色按钮权限
     *
     * @param roleIds 角色ID
     * @return
     */
    public List<RolePermissionDTO> queryRolePermission(List<Long> roleIds) {
        return queryRolePermission(2, roleIds);
    }

    /**
     * 获取角色按钮权限
     *
     * @param type    权限类型，0：一级菜单，1：菜单，2：按钮
     * @param roleIds 角色ID
     * @return
     */
    public List<RolePermissionDTO> queryRolePermission(Integer type, List<Long> roleIds) {
        SysRolePermissionQuery query = SystemAssembler.INSTANCE.toSysRolePermissionQuery(null, roleIds);
        query.setType(type);
        Result<List<SysPermissionDTO>> result = sysPermissionRepository.getRolePermission(query);
        if (!result.isSuccess()) {
            return Collections.emptyList();
        }
        List<SysPermissionDTO> sysPermissionDTOS = result.getData();

        List<RolePermissionDTO> rolePermissionDtos = sysPermissionDTOS.stream().map(sysPermissionPo -> {
            RolePermissionDTO rolePermissionDto = new RolePermissionDTO();
            rolePermissionDto.setId(sysPermissionPo.getId());
            rolePermissionDto.setParentId(sysPermissionPo.getParentId());
            rolePermissionDto.setPermissionId(sysPermissionPo.getKey());
            rolePermissionDto.setAction(sysPermissionPo.getPermission());
            rolePermissionDto.setPath(sysPermissionPo.getPath());
            return rolePermissionDto;
        }).collect(Collectors.toList());
        return rolePermissionDtos;
    }
}

