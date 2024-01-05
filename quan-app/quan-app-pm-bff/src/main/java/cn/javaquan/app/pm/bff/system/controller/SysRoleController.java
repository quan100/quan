package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.app.common.module.system.*;
import cn.javaquan.app.pm.bff.system.feign.SysRoleServiceFeign;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
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
@RequestMapping("/sys/role/")
public class SysRoleController {

    private final SysRoleServiceFeign sysRoleServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysRoleDTO>> page(SysRoleQuery query) {
        return sysRoleServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysRoleDTO> details(@RequestParam Long id) {
        return sysRoleServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysRoleUpdateCommand cmd) {
        return sysRoleServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysRoleAddCommand cmd) {
        return sysRoleServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysRoleAddCommand> cmds) {
        return sysRoleServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysRoleServiceFeign.deleteByIds(ids);
    }

    /**
     * 角色授权
     *
     * @param event
     * @return
     */
    @PutMapping("authorizeRolePermission")
    public Result authorizeRolePermission(@RequestBody @Valid AuthorizeRolePermissionEvent event) {
        sysRoleServiceFeign.authorizeRolePermission(event);
        return Result.success();
    }
}
