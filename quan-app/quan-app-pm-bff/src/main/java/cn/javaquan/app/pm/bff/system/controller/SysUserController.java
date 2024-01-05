package cn.javaquan.app.pm.bff.system.controller;

import cn.javaquan.app.common.module.system.SysUserAddCommand;
import cn.javaquan.app.common.module.system.SysUserUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserVO;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.pm.bff.system.feign.SysUserServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("/sys/user")
public class SysUserController {

    private final SysUserServiceFeign sysUserServiceFeign;


    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping
    public Result<SysUserVO> details(@RequestParam Long id) {
        return sysUserServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody @Valid SysUserUpdateCommand cmd) {
        return sysUserServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody @Valid SysUserAddCommand cmd) {
        return sysUserServiceFeign.save(cmd);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return sysUserServiceFeign.deleteByIds(ids);
    }

}
