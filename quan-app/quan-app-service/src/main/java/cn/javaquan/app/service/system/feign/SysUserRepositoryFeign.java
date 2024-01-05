package cn.javaquan.app.service.system.feign;

import cn.javaquan.app.common.module.system.SysUserAddCommand;
import cn.javaquan.app.common.module.system.SysUserUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserVO;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}")
public interface SysUserRepositoryFeign {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/sys/user")
    Result<SysUserVO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/sys/user")
    Result<Boolean> update(@RequestBody SysUserUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/sys/user")
    Result<Boolean> save(@RequestBody SysUserAddCommand cmd);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/sys/user")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);
}
