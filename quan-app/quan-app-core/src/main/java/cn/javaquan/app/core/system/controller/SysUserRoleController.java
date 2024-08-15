package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.core.system.repository.SysUserRoleRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.system.SysUserRoleAddCommand;
import cn.javaquan.app.common.module.system.SysUserRoleQuery;
import cn.javaquan.app.common.module.system.SysUserRoleUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.system.convert.SysUserRoleAssembler;
import cn.javaquan.app.core.system.entity.SysUserRolePO;
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
@RequestMapping("/core/sys/user/role/")
public class SysUserRoleController {

    private final SysUserRoleRepository sysUserRoleRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysUserRolePO>> page(SysUserRoleQuery query) {
        SysUserRolePO po = SysUserRoleAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysUserRoleRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysUserRolePO> details(@RequestParam Long id) {
        return Result.success(sysUserRoleRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserRoleUpdateCommand cmd) {
        SysUserRolePO po = SysUserRoleAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysUserRoleRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserRoleAddCommand cmd) {
        SysUserRolePO po = SysUserRoleAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysUserRoleRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserRoleAddCommand> cmds) {
        List<SysUserRolePO> pos = SysUserRoleAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysUserRoleRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserRoleRepository.removeByIds(ids));
    }

    /**
     * 根据用户ID查询.
     * @param userId 用户ID
     * @return 查询结果
     */
    @GetMapping("userRole")
    public Result<List<SysUserRolePO>> getUserRole(@RequestParam String userId) {
        return Result.success(sysUserRoleRepository.getUserRole(userId));
    }

    /**
     * 根据角色ID查询数量.
     * @param roleIds 角色ID
     * @return 查询结果
     */
    @GetMapping("count")
    public Result<Integer> getCount(List<Long> roleIds) {
        return Result.success(sysUserRoleRepository.getCount(roleIds));
    }

    /**
     * 删除.
     * @param roleIds 角色ID
     * @return 删除结果
     */
    @DeleteMapping("delByRoleId")
    public Result<Boolean> delByRoleId(@RequestBody List<Long> roleIds) {
        return Result.success(sysUserRoleRepository.delByRoleId(roleIds));
    }

}
