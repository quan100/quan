package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.app.common.module.system.SysUserInfoAddCommand;
import cn.javaquan.app.common.module.system.SysUserInfoDTO;
import cn.javaquan.app.common.module.system.SysUserInfoQuery;
import cn.javaquan.app.common.module.system.SysUserInfoUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.pm.bff.system.feign.SysUserInfoServiceFeign;
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
@RequestMapping("/sys/user/info/")
public class SysUserInfoController {

    private final SysUserInfoServiceFeign sysUserInfoServiceFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<SysUserInfoDTO>> page(SysUserInfoQuery query) {
        return sysUserInfoServiceFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<SysUserInfoDTO> details(@RequestParam Long id) {
        return sysUserInfoServiceFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUserInfoUpdateCommand cmd) {
        return sysUserInfoServiceFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUserInfoAddCommand cmd) {
        return sysUserInfoServiceFeign.save(cmd);
    }

    /**
     * 新增.
     * @param cmds 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<SysUserInfoAddCommand> cmds) {
        return sysUserInfoServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserInfoServiceFeign.deleteByIds(ids);
    }

}
