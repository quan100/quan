package com.quan.app.service.system.controller;

import com.quan.app.common.module.system.SysUserAddCommand;
import com.quan.app.common.module.system.SysUserUpdateCommand;
import com.quan.app.common.module.system.SysUserVO;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.service.SysUserService;
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
@RequestMapping("/service/sys/user")
public class SysUserController {

    private final SysUserService sysUserService;


    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping
    public Result<SysUserVO> details(@RequestParam Long id) {
        return sysUserService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody SysUserUpdateCommand cmd) {
        return sysUserService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody SysUserAddCommand cmd) {
        return sysUserService.save(cmd);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserService.deleteByIds(ids);
    }

}
