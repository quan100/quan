package com.quan.app.pm.bff.system.feign;

import com.quan.app.common.module.system.SysUserAddCommand;
import com.quan.app.common.module.system.SysUserUpdateCommand;
import com.quan.app.common.module.system.SysUserVO;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.system.feign.fallback.SysUserInfoServiceFallback;
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
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = SysUserInfoServiceFallback.class)
public interface SysUserServiceFeign {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/sys/user")
    Result<SysUserVO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/sys/user")
    Result<Boolean> update(@RequestBody SysUserUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/sys/user")
    Result<Boolean> save(@RequestBody SysUserAddCommand cmd);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/sys/user")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);
}
