package cn.javaquan.app.pm.bff.system.feign;

import cn.javaquan.app.pm.bff.system.feign.fallback.SysUserTripartiteAccountServiceFallback;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountDTO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户第三方账户
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = SysUserTripartiteAccountServiceFallback.class)
public interface SysUserTripartiteAccountServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/user/tripartite/account/page")
    Result<PageResult<SysUserTripartiteAccountDTO>> page(@SpringQueryMap SysUserTripartiteAccountQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/sys/user/tripartite/account/details")
    Result<SysUserTripartiteAccountDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/sys/user/tripartite/account/update")
    Result<Boolean> update(@RequestBody SysUserTripartiteAccountUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/sys/user/tripartite/account/save")
    Result<Boolean> save(@RequestBody SysUserTripartiteAccountAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/sys/user/tripartite/account/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysUserTripartiteAccountAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/sys/user/tripartite/account/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据邮箱查询绑定信息
     *
     * @param email
     * @return
     */
    @GetMapping("/service/sys/user/tripartite/account/getByEmail")
    Result<SysUserTripartiteAccountDTO> getByEmail(@RequestParam(value = "email") String email);

    /**
     * 根据查询条件查询绑定信息
     *
     * @param query
     * @return
     */
    @GetMapping("/service/sys/user/tripartite/account/getByTripartite")
    Result<SysUserTripartiteAccountDTO> getByTripartite(@SpringQueryMap SysUserTripartiteAccountQuery query);

}
