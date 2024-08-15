package cn.javaquan.app.pm.bff.system.feign;

import cn.javaquan.app.common.module.auth.AuthQuery;
import cn.javaquan.app.common.module.system.SysUserAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserAccountDTO;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserAccountUpdateCommand;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.app.pm.bff.system.feign.fallback.SysUserAccountServiceFallback;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户账号.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = SysUserAccountServiceFallback.class)
public interface SysUserAccountServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/sys/user/account/page")
    Result<PageResult<SysUserAccountDTO>> page(@SpringQueryMap SysUserAccountQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/sys/user/account/details")
    Result<SysUserAccountDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/sys/user/account/update")
    Result<Boolean> update(@RequestBody SysUserAccountUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/sys/user/account/save")
    Result<Boolean> save(@RequestBody SysUserAccountAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/sys/user/account/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysUserAccountAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/sys/user/account/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 获取用户权限.
     * @param query 查询参数
     * @return 用户权限
     */
    @GetMapping("/service/sys/user/account/userPermission")
    Result<List<UserPermissionTreeDTO>> getUserPermission(@SpringQueryMap AuthQuery query);

    /**
     * 查询账号信息.
     * @param query 查询参数
     * @return 用户账号信息
     */
    @GetMapping("/service/sys/user/account/userAccount")
    Result<SysUserAccountDTO> getUserAccount(@SpringQueryMap SysUserAccountQuery query);

}
