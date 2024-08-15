package cn.javaquan.app.core.system.controller;

import cn.javaquan.app.core.system.repository.SysUserInfoRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.system.SysUserInfoAddCommand;
import cn.javaquan.app.common.module.system.SysUserInfoQuery;
import cn.javaquan.app.common.module.system.SysUserInfoUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.system.convert.SysUserInfoAssembler;
import cn.javaquan.app.core.system.entity.SysUserInfoPO;
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
@RequestMapping("/core/sys/user/info/")
public class SysUserInfoController {

    private final SysUserInfoRepository sysUserInfoRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysUserInfoPO>> page(SysUserInfoQuery query) {
        SysUserInfoPO po = SysUserInfoAssembler.INSTANCE.toQueryPO(query);
        return Result.success(sysUserInfoRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysUserInfoPO> details(@RequestParam Long id) {
        return Result.success(sysUserInfoRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserInfoUpdateCommand cmd) {
        SysUserInfoPO po = SysUserInfoAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(sysUserInfoRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserInfoAddCommand cmd) {
        SysUserInfoPO po = SysUserInfoAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(sysUserInfoRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserInfoAddCommand> cmds) {
        List<SysUserInfoPO> pos = SysUserInfoAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(sysUserInfoRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(sysUserInfoRepository.removeByIds(ids));
    }

    /**
     * 根据用户ID查询信息.
     * @param userId 用户ID
     * @return 查询结果
     */
    @GetMapping("userInfo")
    public Result<SysUserInfoPO> getUserInfo(@RequestParam String userId) {
        return Result.success(sysUserInfoRepository.getUserInfo(userId));
    }

}
