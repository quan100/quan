package com.quan.app.pm.bff.system.controller;

import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.common.module.system.*;
import com.quan.app.pm.bff.system.feign.SysPermissionServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * 系统资源权限配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/permission/")
public class SysPermissionController {

    private final SysPermissionServiceFeign sysPermissionServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysPermissionDTO>> page(SysPermissionQuery query) {
        return sysPermissionServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysPermissionDTO> details(@RequestParam Long id) {
        return sysPermissionServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysPermissionUpdateCommand cmd) {
        return sysPermissionServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysPermissionAddCommand cmd) {
        return sysPermissionServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysPermissionAddCommand> cmds) {
        return sysPermissionServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysPermissionServiceFeign.deleteByIds(ids);
    }

    /**
     * 子权限查询
     *
     * @param query
     * @return
     */
    @GetMapping("subsetPermissions")
    public Result<PageResult<SysPermissionTreeDTO>> getSubsetPermissions(@Valid SubsetPermissionsQuery query) {
        return sysPermissionServiceFeign.getSubsetPermissions(query);
    }

    /**
     * 获取树形结构权限
     *
     * @param query
     * @return
     */
    @GetMapping("treePermissions")
    public Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(@Valid SubsetPermissionsQuery query) {
        if (query.isLoadMenu()) {
            query.setType(Arrays.asList(0, 1));
        }
        return sysPermissionServiceFeign.getTreePermissions(query);
    }

}
