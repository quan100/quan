package com.quan.app.core.system.controller;

import com.quan.common.base.message.PageResult;
import com.quan.app.common.module.system.SysUserInfoAddCommand;
import com.quan.app.common.module.system.SysUserInfoQuery;
import com.quan.app.common.module.system.SysUserInfoUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.core.system.convert.SysUserInfoAssembler;
import com.quan.app.core.system.entity.SysUserInfoPO;
import com.quan.app.core.system.repository.SysUserInfoRepository;
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
@RequestMapping("/core/sys/user/info/")
public class SysUserInfoController {

    private final SysUserInfoRepository sysUserInfoRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(SysUserInfoQuery query) {
        SysUserInfoPO po = SysUserInfoAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysUserInfoRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(sysUserInfoRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody SysUserInfoUpdateCommand cmd) {
        SysUserInfoPO po = SysUserInfoAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysUserInfoRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysUserInfoAddCommand cmd) {
        SysUserInfoPO po = SysUserInfoAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysUserInfoRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<SysUserInfoAddCommand> cmds) {
        List<SysUserInfoPO> pos = SysUserInfoAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysUserInfoRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserInfoRepository.removeByIds(ids));
    }

    /**
     * 根据用户ID查询信息
     *
     * @param userId
     * @return
     */
    @GetMapping("userInfo")
    public Result getUserInfo(@RequestParam String userId) {
        return Result.success(sysUserInfoRepository.getUserInfo(userId));
    }

}
