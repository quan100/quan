package com.quan.app.service.system.service;

import com.quan.app.common.constant.ErrorCodeEnum;
import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.convert.SystemAssembler;
import com.quan.app.common.util.RunUtil;
import com.quan.app.common.util.Validate;
import com.quan.common.base.constant.TopicEnum;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.feign.SysRolePermissionRepositoryFeign;
import com.quan.app.service.system.feign.SysRoleRepositoryFeign;
import com.quan.tools.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@Component
public class SysRoleService {

    private final SysRoleRepositoryFeign sysRoleRepositoryFeign;
    private final SysRolePermissionRepositoryFeign sysRolePermissionRepositoryFeign;
    private final JmsUtil jmsUtil;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<SysRoleDTO>> page(SysRoleQuery query) {
        return sysRoleRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<SysRoleDTO> details(Long id) {
        return sysRoleRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(SysRoleUpdateCommand cmd) {
        Result<SysRoleDTO> result = sysRoleRepositoryFeign.getRole(SystemAssembler.INSTANCE.toSysRoleQuery(cmd.getCode(), cmd.getAppType(), null));
        Validate.isFalse(result.isData() && !result.getData().getId().equals(cmd.getId()), ErrorCodeEnum.PM_ROLE_CODE_EXISTED_ERR);
        return RunUtil.doRun(sysRoleRepositoryFeign.update(cmd), () -> jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(SysRoleAddCommand cmd) {
        Result<SysRoleDTO> result = sysRoleRepositoryFeign.getRole(SystemAssembler.INSTANCE.toSysRoleQuery(cmd.getCode(), cmd.getAppType(), null));
        Validate.isFalse(result.isData(), ErrorCodeEnum.PM_ROLE_CODE_EXISTED_ERR);
        return sysRoleRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<SysRoleAddCommand> cmds) {
        return sysRoleRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return RunUtil.doRun(sysRoleRepositoryFeign.deleteByIds(ids), () -> jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true));
    }

    /**
     * 获取角色权限配置
     * <p>
     * 角色拥有的权限ID
     *
     * @param id 角色主键id
     * @return
     */
    public Result<List<Long>> getRolePermissionIds(@RequestParam Long id) {
        SysRolePermissionQuery query = SystemAssembler.INSTANCE.toSysRolePermissionQuery(id, null);
        return sysRolePermissionRepositoryFeign.getRolePermissionIds(query);
    }

    /**
     * 角色授权
     *
     * @param event
     * @return
     */
    public void authorizeRolePermission(AuthorizeRolePermissionEvent event) {
        // 删除原权限
        Result<Integer> countResult = sysRolePermissionRepositoryFeign.count(SystemAssembler.INSTANCE.toSysRolePermissionQuery(event.getRoleId(), null));
        if (countResult.isData() && countResult.getData() > 0) {
            Result<Boolean> delResult = sysRolePermissionRepositoryFeign.delRolePermission(SystemAssembler.INSTANCE.toSysRolePermissionEvent(event.getRoleId()));
            Validate.isTrue(delResult.isSuccess(), "角色授权失败！");
        }
        // 新增新权限
        List<Long> permissionIdList = event.getPermissionIdList();
        if (Validate.isEmpty(permissionIdList)) {
            return;
        }
        List<SysRolePermissionAddCommand> cmds = permissionIdList.stream().map(permissionId -> {
            SysRolePermissionAddCommand cmd = new SysRolePermissionAddCommand();
            cmd.setRoleId(event.getRoleId());
            cmd.setPermissionId(permissionId);
            return cmd;
        }).collect(Collectors.toList());
        Validate.isTrue(sysRolePermissionRepositoryFeign.saveBatch(cmds).isSuccess(), "角色授权失败！");

        jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true);
    }
}
