package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.core.system.repository.SysRoleRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.system.SysRoleAddCommand;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.module.system.SysRoleUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.system.convert.SysRoleAssembler;
import cn.javaquan.app.core.system.entity.SysRolePO;
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
 * 角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/sys/role/")
public class SysRoleController {

    private final SysRoleRepository sysRoleRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysRolePO>> page(SysRoleQuery query) {
        SysRolePO po = SysRoleAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysRoleRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysRolePO> details(@RequestParam Long id) {
        return Result.success(sysRoleRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysRoleUpdateCommand cmd) {
        SysRolePO po = SysRoleAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysRoleRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysRoleAddCommand cmd) {
        SysRolePO po = SysRoleAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysRoleRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysRoleAddCommand> cmds) {
        List<SysRolePO> pos = SysRoleAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysRoleRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysRoleRepository.deleteByIds(ids));
    }

    /**
     * 查询角色.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("role")
    public Result<SysRolePO> getRole(SysRoleQuery query) {
        return Result.success(sysRoleRepository.getRole(query));
    }

    /**
     * 查询角色.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("roles")
    public Result<List<SysRolePO>> getRoles(SysRoleQuery query) {
        return Result.success(sysRoleRepository.getRoles(query));
    }

}
