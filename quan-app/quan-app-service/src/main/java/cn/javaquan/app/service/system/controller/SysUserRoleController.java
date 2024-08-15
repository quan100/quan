package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.system.SysUserRoleAddCommand;
import cn.javaquan.app.common.module.system.SysUserRoleDTO;
import cn.javaquan.app.common.module.system.SysUserRoleQuery;
import cn.javaquan.app.common.module.system.SysUserRoleUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/sys/user/role/")
public class SysUserRoleController {

    private final SysUserRoleService sysUserRoleService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysUserRoleDTO>> page(SysUserRoleQuery query) {
        return sysUserRoleService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysUserRoleDTO> details(@RequestParam Long id) {
        return sysUserRoleService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserRoleUpdateCommand cmd) {
        return sysUserRoleService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserRoleAddCommand cmd) {
        return sysUserRoleService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserRoleAddCommand> cmds) {
        return sysUserRoleService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserRoleService.deleteByIds(ids);
    }

    /**
     * 根据用户ID查询.
     * @param userId 用户id
     * @return 用户角色
     */
    @GetMapping("userRole")
    public Result<List<SysUserRoleDTO>> getUserRole(@RequestParam String userId) {
        return sysUserRoleService.getUserRole(userId);
    }

    /**
     * 根据角色ID查询数量.
     * @param roleIds 角色id
     * @return 角色数量
     */
    @GetMapping("count")
    public Result<Integer> getCount(List<Long> roleIds) {
        return sysUserRoleService.getCount(roleIds);
    }

    /**
     * 删除.
     * @param roleIds 角色id
     * @return 操作是否成功
     */
    @DeleteMapping("delByRoleId")
    public Result<Boolean> delByRoleId(@RequestBody List<Long> roleIds) {
        return sysUserRoleService.delByRoleId(roleIds);
    }

}
