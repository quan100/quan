package cn.javaquan.app.service.system.controller;

import cn.javaquan.app.common.module.system.SysUserAddCommand;
import cn.javaquan.app.common.module.system.SysUserUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserVO;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.service.SysUserService;
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
@RequestMapping("/service/sys/user")
public class SysUserController {

    private final SysUserService sysUserService;

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping
    public Result<SysUserVO> details(@RequestParam Long id) {
        return sysUserService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody SysUserUpdateCommand cmd) {
        return sysUserService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody SysUserAddCommand cmd) {
        return sysUserService.save(cmd);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserService.deleteByIds(ids);
    }

}
