package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.module.system.AuthorizeRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRoleAddCommand;
import cn.javaquan.app.common.module.system.SysRoleDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionAddCommand;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.module.system.SysRoleUpdateCommand;
import cn.javaquan.app.common.module.system.convert.SystemAssembler;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.service.system.feign.SysRolePermissionRepositoryFeign;
import cn.javaquan.app.service.system.feign.SysRoleRepositoryFeign;
import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class SysRoleService {

    private final SysRoleRepositoryFeign sysRoleRepositoryFeign;

    private final SysRolePermissionRepositoryFeign sysRolePermissionRepositoryFeign;

    private final JmsUtil jmsUtil;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<SysRoleDTO>> page(SysRoleQuery query) {
        return sysRoleRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<SysRoleDTO> details(Long id) {
        return sysRoleRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(SysRoleUpdateCommand cmd) {
        Result<SysRoleDTO> result = sysRoleRepositoryFeign
            .getRole(SystemAssembler.INSTANCE.toSysRoleQuery(cmd.getCode(), cmd.getAppType(), null));
        Validate.isFalse(result.isData() && !result.getData().getId().equals(cmd.getId()),
                ErrorCodeEnum.PM_ROLE_CODE_EXISTED_ERR);
        return RunUtil.doRun(sysRoleRepositoryFeign.update(cmd),
                () -> jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(SysRoleAddCommand cmd) {
        Result<SysRoleDTO> result = sysRoleRepositoryFeign
            .getRole(SystemAssembler.INSTANCE.toSysRoleQuery(cmd.getCode(), cmd.getAppType(), null));
        Validate.isFalse(result.isData(), ErrorCodeEnum.PM_ROLE_CODE_EXISTED_ERR);
        return sysRoleRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<SysRoleAddCommand> cmds) {
        return sysRoleRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return RunUtil.doRun(sysRoleRepositoryFeign.deleteByIds(ids),
                () -> jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true));
    }

    /**
     * 获取角色权限配置.
     * <p>
     * 角色拥有的权限ID
     * @param id 角色主键id
     * @return 权限ID列表
     */
    public Result<List<Long>> getRolePermissionIds(@RequestParam Long id) {
        SysRolePermissionQuery query = SystemAssembler.INSTANCE.toSysRolePermissionQuery(id, null);
        return sysRolePermissionRepositoryFeign.getRolePermissionIds(query);
    }

    /**
     * 角色授权.
     * @param event 角色授权事件参数
     */
    public void authorizeRolePermission(AuthorizeRolePermissionEvent event) {
        // 删除原权限
        Result<Integer> countResult = sysRolePermissionRepositoryFeign
            .count(SystemAssembler.INSTANCE.toSysRolePermissionQuery(event.getRoleId(), null));
        if (countResult.isData() && countResult.getData() > 0) {
            Result<Boolean> delResult = sysRolePermissionRepositoryFeign
                .delRolePermission(SystemAssembler.INSTANCE.toSysRolePermissionEvent(event.getRoleId()));
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
