package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.app.common.module.system.SubsetPermissionsQuery;
import cn.javaquan.app.common.module.system.SysPermissionAddCommand;
import cn.javaquan.app.common.module.system.SysPermissionDTO;
import cn.javaquan.app.common.module.system.SysPermissionQuery;
import cn.javaquan.app.common.module.system.SysPermissionTreeDTO;
import cn.javaquan.app.common.module.system.SysPermissionUpdateCommand;
import cn.javaquan.app.pm.bff.system.feign.SysPermissionServiceFeign;
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
import java.util.Arrays;
import java.util.List;

/**
 * 系统资源权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/permission/")
public class SysPermissionController {

    private final SysPermissionServiceFeign sysPermissionServiceFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysPermissionDTO>> page(SysPermissionQuery query) {
        return sysPermissionServiceFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysPermissionDTO> details(@RequestParam Long id) {
        return sysPermissionServiceFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysPermissionUpdateCommand cmd) {
        return sysPermissionServiceFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysPermissionAddCommand cmd) {
        return sysPermissionServiceFeign.save(cmd);
    }

    /**
     * 新增.
     * @param cmds 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysPermissionAddCommand> cmds) {
        return sysPermissionServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysPermissionServiceFeign.deleteByIds(ids);
    }

    /**
     * 子权限查询.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("subsetPermissions")
    public Result<PageResult<SysPermissionTreeDTO>> getSubsetPermissions(@Valid SubsetPermissionsQuery query) {
        return sysPermissionServiceFeign.getSubsetPermissions(query);
    }

    /**
     * 获取树形结构权限.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("treePermissions")
    public Result<PageResult<SysPermissionTreeDTO>> getTreePermissions(@Valid SubsetPermissionsQuery query) {
        if (query.isLoadMenu()) {
            query.setType(Arrays.asList(0, 1));
        }
        return sysPermissionServiceFeign.getTreePermissions(query);
    }

}
