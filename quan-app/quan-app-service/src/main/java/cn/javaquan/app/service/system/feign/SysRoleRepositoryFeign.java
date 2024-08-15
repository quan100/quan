package cn.javaquan.app.service.system.feign;

import cn.javaquan.app.common.module.system.SysRoleAddCommand;
import cn.javaquan.app.common.module.system.SysRoleDTO;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.module.system.SysRoleUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.feign.fallback.SysRoleRepositoryFallback;
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
 * 角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = SysRoleRepositoryFallback.class)
public interface SysRoleRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/sys/role/page")
    Result<PageResult<SysRoleDTO>> page(@SpringQueryMap SysRoleQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/sys/role/details")
    Result<SysRoleDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/sys/role/update")
    Result<Boolean> update(@RequestBody SysRoleUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/sys/role/save")
    Result<Boolean> save(@RequestBody SysRoleAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/sys/role/saveBatch")
    Result saveBatch(@RequestBody List<SysRoleAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/sys/role/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 查询角色.
     * @param query 查询参数
     * @return 角色数据
     */
    @GetMapping("/core/sys/role/role")
    Result<SysRoleDTO> getRole(@SpringQueryMap SysRoleQuery query);

    /**
     * 查询角色.
     * @param query 查询参数
     * @return 角色数据
     */
    @GetMapping("/core/sys/role/roles")
    Result<List<SysRoleDTO>> getRoles(@SpringQueryMap SysRoleQuery query);

}
