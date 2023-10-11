package com.quan.app.service.system.controller;

import com.quan.app.common.module.system.*;
import com.quan.app.common.module.system.*;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.service.SysRoleService;
import com.quan.tools.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/role/")
public class SysRoleController {

    private final SysRoleService sysRoleService;
    private final JmsUtil jmsUtil;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysRoleDTO>> page(SysRoleQuery query) {
        return sysRoleService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysRoleDTO> details(@RequestParam Long id) {
        return sysRoleService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysRoleUpdateCommand cmd) {
        return sysRoleService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysRoleAddCommand cmd) {
        return sysRoleService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysRoleAddCommand> cmds) {
        return sysRoleService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysRoleService.deleteByIds(ids);
    }

    /**
     * 获取角色权限配置
     * <p>
     * 角色拥有的权限ID
     *
     * @param id 角色主键id
     * @return
     */
    @GetMapping("rolePermissionIds")
    public Result<List<Long>> getRolePermissionIds(@RequestParam Long id) {
        return sysRoleService.getRolePermissionIds(id);
    }

    /**
     * 角色授权
     *
     * @param event
     * @return
     */
    @PutMapping("authorizeRolePermission")
    public void authorizeRolePermission(@RequestBody @Valid AuthorizeRolePermissionEvent event) {
        sysRoleService.authorizeRolePermission(event);
    }
}
