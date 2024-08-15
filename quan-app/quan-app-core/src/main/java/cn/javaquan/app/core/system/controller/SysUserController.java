package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.core.system.repository.SysUserRepository;
import cn.javaquan.app.common.module.system.SysUserAddCommand;
import cn.javaquan.app.common.module.system.SysUserUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserVO;
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

import java.util.List;

/**
 * 用户信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/sys/user")
public class SysUserController {

    private final SysUserRepository sysUserRepository;

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping
    public Result<SysUserVO> details(@RequestParam Long id) {
        return Result.success(sysUserRepository.details(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody SysUserUpdateCommand cmd) {
        return Result.success(sysUserRepository.update(cmd));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody SysUserAddCommand cmd) {
        return Result.success(sysUserRepository.save(cmd));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserRepository.deleteByIds(ids));
    }

}
