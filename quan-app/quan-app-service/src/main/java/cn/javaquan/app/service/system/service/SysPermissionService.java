package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.module.system.*;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.service.system.feign.SysPermissionRepositoryFeign;
import cn.javaquan.app.service.system.feign.SysRolePermissionRepositoryFeign;
import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.id.ID;
import cn.javaquan.tools.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 系统资源权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@Component
public class SysPermissionService {

    private final SysPermissionRepositoryFeign sysPermissionRepositoryFeign;
    private final SysRolePermissionRepositoryFeign sysRolePermissionRepositoryFeign;
    private final JmsUtil jmsUtil;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<SysPermissionDTO>> page(SysPermissionQuery query) {
        return sysPermissionRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<SysPermissionDTO> details(Long id) {
        return sysPermissionRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(SysPermissionUpdateCommand cmd) {
        Result<SysPermissionDTO> result = sysPermissionRepositoryFeign.details(cmd.getId());
        Validate.isTrue(result.isData(), ErrorCodeEnum.DATA_NOT_EXIST_ERROR);
        SysPermissionDTO old = result.getData();

        if (!Validate.defaultValue(old.getParentKeys()).equals(cmd.getParentKeys())) {
            cmd.setParentKeys(toParentKeys(cmd.getParentId()));
        }

        return RunUtil.doRun(sysPermissionRepositoryFeign.update(cmd), () -> {
            // 仅按钮权限触发网关刷新
            if (cmd.getType() > 1 && !cmd.getPermission().equals(old.getPermission())) {
                jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true);
            }
        });
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(SysPermissionAddCommand cmd) {
        cmd.setKey(ID.genId());
        cmd.setParentKeys(toParentKeys(cmd.getParentId()));

        return RunUtil.doRun(sysPermissionRepositoryFeign.save(cmd), () -> {
            // 仅按钮权限触发网关刷新
            if (cmd.getType() > 1) {
                jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true);
            }
        });
    }

    /**
     * 获取上级权限KEY
     *
     * @param parentId
     */
    private String toParentKeys(Long parentId) {
        if (Validate.defaultValue(parentId) != 0) {
            Result<SysPermissionDTO> result = sysPermissionRepositoryFeign.details(parentId);
            Validate.isTrue(result.isData(), ErrorCodeEnum.PM_PERMISSION_PARENT_NOT_FOUND);
            SysPermissionDTO parentDTO = result.getData();
            return parentDTO.getKey();
        }
        return "";
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<SysPermissionAddCommand> cmds) {
        return sysPermissionRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysPermissionRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 子权限查询
     *
     * @param query
     * @return
     */
    public Result<PageResult<SysPermissionTreeDTO>> getSubsetPermissions(SubsetPermissionsQuery query) {
        return sysPermissionRepositoryFeign.getSubsetPermissions(query);
    }

    /**
     * 获取树形结构权限
     *
     * @param query
     * @return
     */
    public Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(SubsetPermissionsQuery query) {
        return sysPermissionRepositoryFeign.getTreePermissions(query);
    }

    /**
     * 获取系统权限配置
     * 基于路径匹配权限
     *
     * @param defaultAuth 当菜单资源未配置权限时，默认配置该参数作为菜单资源默认权限
     * @return
     */
    public Result<List<String>> getPerms(String defaultAuth) {
        Result<List<SysPermissionDTO>> sysPermissionResult = sysPermissionRepositoryFeign.getPerms();
        if (!sysPermissionResult.isData()) {
            return Result.success(Collections.emptyList());
        }
        List<SysPermissionDTO> sysPermissionPos = sysPermissionResult.getData();

        // 获取权限角色
        Result<List<PermissionRoleDTO>> rolePermissionResult = sysRolePermissionRepositoryFeign.getPermissionRoles();
        Map<Long, List<PermissionRoleDTO>> rolePermissionGroup;
        if (rolePermissionResult.isData()) {
            List<PermissionRoleDTO> permissionRoleDTOS = rolePermissionResult.getData();
            rolePermissionGroup = permissionRoleDTOS.stream().collect(Collectors.groupingBy(PermissionRoleDTO::getPermissionId));
        } else {
            rolePermissionGroup = Collections.emptyMap();
        }

        List<String> authSource = sysPermissionPos.stream().map(sysPermissionPo -> {
            if (Validate.isBlank(sysPermissionPo.getPath())) {
                return null;
            }
            StringBuffer sb = new StringBuffer().append(sysPermissionPo.getPath()).append("=");
            String permStr = sysPermissionPo.getPermission();
            if (Validate.isBlank(permStr)) {
                sb.append(defaultAuth);
            } else {
                sb.append(permStr);
            }
            List<PermissionRoleDTO> permissionRoleEntities = rolePermissionGroup.get(sysPermissionPo.getId());
            if (!CollectionUtils.isEmpty(permissionRoleEntities)) {
                sb.append("&").append(permissionRoleEntities.stream().map(PermissionRoleDTO::getCode).collect(Collectors.joining(",")));
            }
            return sb.toString();
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Result.success(authSource);
    }

}
