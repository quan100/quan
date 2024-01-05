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
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/core/sys/role/")
public class SysRoleController {

    private final SysRoleRepository sysRoleRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(SysRoleQuery query) {
        SysRolePO po = SysRoleAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysRoleRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(sysRoleRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody SysRoleUpdateCommand cmd) {
        SysRolePO po = SysRoleAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysRoleRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysRoleAddCommand cmd) {
        SysRolePO po = SysRoleAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysRoleRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<SysRoleAddCommand> cmds) {
        List<SysRolePO> pos = SysRoleAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysRoleRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysRoleRepository.deleteByIds(ids));
    }

    /**
     * 查询角色
     *
     * @param query
     * @return
     */
    @GetMapping("role")
    public Result getRole(SysRoleQuery query) {
        return Result.success(sysRoleRepository.getRole(query));
    }

    /**
     * 查询角色
     *
     * @param query
     * @return
     */
    @GetMapping("roles")
    public Result getRoles(SysRoleQuery query) {
        return Result.success(sysRoleRepository.getRoles(query));
    }
}
