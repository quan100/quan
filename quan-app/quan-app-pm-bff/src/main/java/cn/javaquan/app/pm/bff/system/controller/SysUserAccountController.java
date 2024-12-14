package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserAccountVO;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.app.pm.bff.system.convert.AuthEntityAssembler;
import cn.javaquan.app.pm.bff.system.feign.SysUserAccountServiceFeign;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.security.common.annotation.AuthUser;
import cn.javaquan.security.common.dto.entity.AuthEntity;
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
 * 用户账号.
 *
 * @author javaquan
 * @since 2.3.2
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys/user/account/")
public class SysUserAccountController {

    private final SysUserAccountServiceFeign sysUserAccountServiceFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysUserAccountVO>> page(SysUserAccountQuery query) {
        return sysUserAccountServiceFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysUserAccountVO> details(@RequestParam Long id) {
        return sysUserAccountServiceFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserAccountUpdateCommand cmd) {
        return sysUserAccountServiceFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserAccountAddCommand cmd) {
        return sysUserAccountServiceFeign.save(cmd);
    }

    /**
     * 新增.
     * @param cmds 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserAccountAddCommand> cmds) {
        return sysUserAccountServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserAccountServiceFeign.deleteByIds(ids);
    }

    /**
     * 获取用户权限列表.
     * @param authEntity 用户认证信息
     * @return 用户权限列表
     */
    @GetMapping("userPermission")
    public Result<List<UserPermissionTreeDTO>> getUserPermission(@AuthUser AuthEntity authEntity) {
        return sysUserAccountServiceFeign.getUserPermission(AuthEntityAssembler.INSTANCE.toAuthQuery(authEntity));
    }

}
