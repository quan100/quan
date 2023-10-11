package com.quan.app.service.system.feign;

import com.quan.app.common.module.system.SysUserInfoAddCommand;
import com.quan.app.common.module.system.SysUserInfoDTO;
import com.quan.app.common.module.system.SysUserInfoQuery;
import com.quan.app.common.module.system.SysUserInfoUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.feign.fallback.SysUserInfoRepositoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = SysUserInfoRepositoryFallback.class)
public interface SysUserInfoRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/user/info/page")
    Result<PageResult<SysUserInfoDTO>> page(@SpringQueryMap SysUserInfoQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/sys/user/info/details")
    Result<SysUserInfoDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/sys/user/info/update")
    Result<Boolean> update(@RequestBody SysUserInfoUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/sys/user/info/save")
    Result<Boolean> save(@RequestBody SysUserInfoAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/sys/user/info/saveBatch")
    Result saveBatch(@RequestBody List<SysUserInfoAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/sys/user/info/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据用户ID查询信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/core/sys/user/info/userInfo")
    Result<SysUserInfoDTO> getUserInfo(@RequestParam(value = "userId") String userId);

}
