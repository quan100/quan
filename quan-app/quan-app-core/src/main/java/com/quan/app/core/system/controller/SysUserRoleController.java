package com.quan.app.core.system.controller;

import com.quan.common.base.message.PageResult;
import com.quan.app.common.module.system.SysUserRoleAddCommand;
import com.quan.app.common.module.system.SysUserRoleQuery;
import com.quan.app.common.module.system.SysUserRoleUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.core.system.convert.SysUserRoleAssembler;
import com.quan.app.core.system.entity.SysUserRolePO;
import com.quan.app.core.system.repository.SysUserRoleRepository;
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
@RequestMapping("/core/sys/user/role/")
public class SysUserRoleController {

    private final SysUserRoleRepository sysUserRoleRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(SysUserRoleQuery query) {
        SysUserRolePO po = SysUserRoleAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysUserRoleRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(sysUserRoleRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody SysUserRoleUpdateCommand cmd) {
        SysUserRolePO po = SysUserRoleAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysUserRoleRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysUserRoleAddCommand cmd) {
        SysUserRolePO po = SysUserRoleAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysUserRoleRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<SysUserRoleAddCommand> cmds) {
        List<SysUserRolePO> pos = SysUserRoleAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysUserRoleRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserRoleRepository.removeByIds(ids));
    }

    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    @GetMapping("userRole")
    public Result getUserRole(@RequestParam String userId) {
        return Result.success(sysUserRoleRepository.getUserRole(userId));
    }

    /**
     * 根据角色ID查询数量
     *
     * @param roleIds
     * @return
     */
    @GetMapping("count")
    public Result getCount(List<Long> roleIds) {
        return Result.success(sysUserRoleRepository.getCount(roleIds));
    }

    /**
     * 删除
     *
     * @param roleIds
     * @return
     */
    @DeleteMapping("delByRoleId")
    public Result delByRoleId(@RequestBody List<Long> roleIds) {
        return Result.success(sysUserRoleRepository.delByRoleId(roleIds));
    }
}
