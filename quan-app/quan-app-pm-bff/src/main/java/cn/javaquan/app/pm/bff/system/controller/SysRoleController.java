package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.app.common.module.system.AuthorizeRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRoleAddCommand;
import cn.javaquan.app.common.module.system.SysRoleDTO;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.module.system.SysRoleUpdateCommand;
import cn.javaquan.app.pm.bff.system.feign.SysRoleServiceFeign;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
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
@RequestMapping("/sys/role/")
public class SysRoleController {

    private final SysRoleServiceFeign sysRoleServiceFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysRoleDTO>> page(SysRoleQuery query) {
        return sysRoleServiceFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysRoleDTO> details(@RequestParam Long id) {
        return sysRoleServiceFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysRoleUpdateCommand cmd) {
        return sysRoleServiceFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysRoleAddCommand cmd) {
        return sysRoleServiceFeign.save(cmd);
    }

    /**
     * 新增.
     * @param cmds 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysRoleAddCommand> cmds) {
        return sysRoleServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysRoleServiceFeign.deleteByIds(ids);
    }

    /**
     * 角色授权.
     * @param event 角色授权事件参数
     * @return 角色授权操作是否成功
     */
    @PutMapping("authorizeRolePermission")
    public Result authorizeRolePermission(@RequestBody @Valid AuthorizeRolePermissionEvent event) {
        sysRoleServiceFeign.authorizeRolePermission(event);
        return Result.success();
    }

}
