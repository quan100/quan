package com.quan.app.service.system.controller;

import com.quan.app.common.module.system.SysUserInfoAddCommand;
import com.quan.app.common.module.system.SysUserInfoDTO;
import com.quan.app.common.module.system.SysUserInfoQuery;
import com.quan.app.common.module.system.SysUserInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.service.SysUserInfoService;
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
@RequestMapping("/service/sys/user/info/")
public class SysUserInfoController {

    private final SysUserInfoService sysUserInfoService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<SysUserInfoDTO>> page(SysUserInfoQuery query) {
        return sysUserInfoService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<SysUserInfoDTO> details(@RequestParam Long id) {
        return sysUserInfoService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserInfoUpdateCommand cmd) {
        return sysUserInfoService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserInfoAddCommand cmd) {
        return sysUserInfoService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserInfoAddCommand> cmds) {
        return sysUserInfoService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserInfoService.deleteByIds(ids);
    }

    /**
     * 根据用户ID查询信息
     *
     * @param userId
     * @return
     */
    @GetMapping("userInfo")
    public Result<SysUserInfoDTO> getUserInfo(@RequestParam String userId) {
        return sysUserInfoService.getUserInfo(userId);
    }

}
