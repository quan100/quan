package com.quan.app.service.system.feign;

import com.quan.app.common.module.system.SysUserAccountAddCommand;
import com.quan.app.common.module.system.SysUserAccountDTO;
import com.quan.app.common.module.system.SysUserAccountQuery;
import com.quan.app.common.module.system.SysUserAccountUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.system.feign.fallback.SysUserAccountRepositoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户账号
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = SysUserAccountRepositoryFallback.class)
public interface SysUserAccountRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/user/account/page")
    Result<PageResult<SysUserAccountDTO>> page(@SpringQueryMap SysUserAccountQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/sys/user/account/details")
    Result<SysUserAccountDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/sys/user/account/update")
    Result<Boolean> update(@RequestBody SysUserAccountUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/sys/user/account/save")
    Result<Boolean> save(@RequestBody SysUserAccountAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/sys/user/account/saveBatch")
    Result saveBatch(@RequestBody List<SysUserAccountAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/sys/user/account/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 查询账号信息
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/user/account/userAccount")
    Result<SysUserAccountDTO> getUserAccount(@SpringQueryMap SysUserAccountQuery query);

}
