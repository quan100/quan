package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.pm.bff.system.feign.SysRolePermissionServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色权限配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/role/permission/")
public class SysRolePermissionController {

    private final SysRolePermissionServiceFeign sysRolePermissionServiceFeign;

    // /**
    // * 查询列表
    // *
    // * @param query
    // * @return
    // */
    // @GetMapping("page")
    // public Result<PageResult<SysRolePermissionDTO>> page(SysRolePermissionQuery query)
    // {
    // return sysRolePermissionServiceFeign.page(query);
    // }
    //
    // /**
    // * 根据ID查询
    // *
    // * @param id
    // * @return
    // */
    // @GetMapping("details")
    // public Result<SysRolePermissionDTO> details(@RequestParam Long id) {
    // return sysRolePermissionServiceFeign.details(id);
    // }
    //
    // /**
    // * 根据主键更新
    // *
    // * @param cmd
    // * @return
    // */
    // @PutMapping("update")
    // public Result<Boolean> update(@RequestBody SysRolePermissionUpdateCommand cmd) {
    // return sysRolePermissionServiceFeign.update(cmd);
    // }
    //
    // /**
    // * 新增
    // *
    // * @param cmd
    // * @return
    // */
    // @PostMapping("save")
    // public Result<Boolean> save(@RequestBody SysRolePermissionAddCommand cmd) {
    // return sysRolePermissionServiceFeign.save(cmd);
    // }
    //
    // /**
    // * 新增
    // *
    // * @param cmds
    // * @return
    // */
    // @PostMapping("saveBatch")
    // public Result<Boolean> saveBatch(@RequestBody List<SysRolePermissionAddCommand>
    // cmds) {
    // return sysRolePermissionServiceFeign.saveBatch(cmds);
    // }
    //
    // /**
    // * 删除
    // *
    // * @param ids
    // * @return
    // */
    // @DeleteMapping("deleteByIds")
    // public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
    // return sysRolePermissionServiceFeign.deleteByIds(ids);
    // }

    /**
     * 获取角色对应的权限ID列表.
     * @param id 角色数据主键
     * @return 权限id列表
     */
    @GetMapping("rolePermissionIds")
    public Result<List<Long>> rolePermissionIds(@RequestParam Long id) {
        return sysRolePermissionServiceFeign.rolePermissionIds(id);
    }

}
