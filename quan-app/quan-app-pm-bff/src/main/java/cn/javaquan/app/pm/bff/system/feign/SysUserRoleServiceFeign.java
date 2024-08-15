package cn.javaquan.app.pm.bff.system.feign;

import cn.javaquan.app.pm.bff.system.feign.fallback.SysUserRoleServiceFallback;
import cn.javaquan.app.common.module.system.SysUserRoleAddCommand;
import cn.javaquan.app.common.module.system.SysUserRoleDTO;
import cn.javaquan.app.common.module.system.SysUserRoleQuery;
import cn.javaquan.app.common.module.system.SysUserRoleUpdateCommand;
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
 * 用户角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = SysUserRoleServiceFallback.class)
public interface SysUserRoleServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/sys/user/role/page")
    Result<PageResult<SysUserRoleDTO>> page(@SpringQueryMap SysUserRoleQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/sys/user/role/details")
    Result<SysUserRoleDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/sys/user/role/update")
    Result<Boolean> update(@RequestBody SysUserRoleUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/sys/user/role/save")
    Result<Boolean> save(@RequestBody SysUserRoleAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/sys/user/role/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysUserRoleAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/sys/user/role/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据用户ID查询.
     * @param userId 用户id
     * @return 用户角色
     */
    @GetMapping("/service/sys/user/role/userRole")
    Result<List<SysUserRoleDTO>> getUserRole(@RequestParam String userId);

    /**
     * 根据角色ID查询数量.
     * @param roleIds 角色id
     * @return 角色数量
     */
    @GetMapping("/service/sys/user/role/count")
    Result<Integer> getCount(List<Long> roleIds);

    /**
     * 删除.
     * @param roleIds 角色id
     * @return 操作是否成功
     */
    @DeleteMapping("/service/sys/user/role/delByRoleId")
    Result<Boolean> delByRoleId(@RequestBody List<Long> roleIds);

}
