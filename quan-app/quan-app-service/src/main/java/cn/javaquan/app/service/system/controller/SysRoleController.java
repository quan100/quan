package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.system.AuthorizeRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRoleAddCommand;
import cn.javaquan.app.common.module.system.SysRoleDTO;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.module.system.SysRoleUpdateCommand;
import cn.javaquan.app.service.system.service.SysRoleService;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/role/")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    private final JmsUtil jmsUtil;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysRoleDTO>> page(SysRoleQuery query) {
        return sysRoleService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysRoleDTO> details(@RequestParam Long id) {
        return sysRoleService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysRoleUpdateCommand cmd) {
        return sysRoleService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysRoleAddCommand cmd) {
        return sysRoleService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysRoleAddCommand> cmds) {
        return sysRoleService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysRoleService.deleteByIds(ids);
    }

    /**
     * 获取角色权限配置.
     * <p>
     * 角色拥有的权限ID
     * @param id 角色主键id
     * @return 权限ID
     */
    @GetMapping("rolePermissionIds")
    public Result<List<Long>> getRolePermissionIds(@RequestParam Long id) {
        return sysRoleService.getRolePermissionIds(id);
    }

    /**
     * 角色授权.
     * @param event 角色授权事件参数
     */
    @PutMapping("authorizeRolePermission")
    public void authorizeRolePermission(@RequestBody @Valid AuthorizeRolePermissionEvent event) {
        sysRoleService.authorizeRolePermission(event);
    }

}
