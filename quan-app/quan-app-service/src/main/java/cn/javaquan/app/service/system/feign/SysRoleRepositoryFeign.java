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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = SysRoleRepositoryFallback.class)
public interface SysRoleRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/role/page")
    Result<PageResult<SysRoleDTO>> page(@SpringQueryMap SysRoleQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/sys/role/details")
    Result<SysRoleDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/sys/role/update")
    Result<Boolean> update(@RequestBody SysRoleUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/sys/role/save")
    Result<Boolean> save(@RequestBody SysRoleAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/sys/role/saveBatch")
    Result saveBatch(@RequestBody List<SysRoleAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/sys/role/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 查询角色
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/role/role")
    Result<SysRoleDTO> getRole(@SpringQueryMap SysRoleQuery query);


    /**
     * 查询角色
     *
     * @param query
     * @return
     */
    @GetMapping("/core/sys/role/roles")
    Result<List<SysRoleDTO>> getRoles(@SpringQueryMap SysRoleQuery query);

}
