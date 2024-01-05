package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.system.SysUserRoleAddCommand;
import cn.javaquan.app.common.module.system.SysUserRoleDTO;
import cn.javaquan.app.common.module.system.SysUserRoleQuery;
import cn.javaquan.app.common.module.system.SysUserRoleUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/user/role/")
public class SysUserRoleController {

    private final SysUserRoleService sysUserRoleService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysUserRoleDTO>> page(SysUserRoleQuery query) {
        return sysUserRoleService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysUserRoleDTO> details(@RequestParam Long id) {
        return sysUserRoleService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserRoleUpdateCommand cmd) {
        return sysUserRoleService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserRoleAddCommand cmd) {
        return sysUserRoleService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserRoleAddCommand> cmds) {
        return sysUserRoleService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserRoleService.deleteByIds(ids);
    }

    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    @GetMapping("userRole")
    public Result<List<SysUserRoleDTO>> getUserRole(@RequestParam String userId) {
        return sysUserRoleService.getUserRole(userId);
    }

    /**
     * 根据角色ID查询数量
     *
     * @param roleIds
     * @return
     */
    @GetMapping("count")
    public Result<Integer> getCount(List<Long> roleIds) {
        return sysUserRoleService.getCount(roleIds);
    }

    /**
     * 删除
     *
     * @param roleIds
     * @return
     */
    @DeleteMapping("delByRoleId")
    public Result<Boolean> delByRoleId(@RequestBody List<Long> roleIds) {
        return sysUserRoleService.delByRoleId(roleIds);
    }

}
