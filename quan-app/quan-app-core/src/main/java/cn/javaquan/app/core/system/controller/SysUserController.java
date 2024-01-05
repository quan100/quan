package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.core.system.repository.SysUserRepository;
import cn.javaquan.app.common.module.system.SysUserAddCommand;
import cn.javaquan.app.common.module.system.SysUserUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserVO;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户信息
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/sys/user")
public class SysUserController {

    private final SysUserRepository sysUserRepository;


    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping
    public Result<SysUserVO> details(@RequestParam Long id) {
        return Result.success(sysUserRepository.details(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody SysUserUpdateCommand cmd) {
        return Result.success(sysUserRepository.update(cmd));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody SysUserAddCommand cmd) {
        return Result.success(sysUserRepository.save(cmd));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserRepository.deleteByIds(ids));
    }

}
