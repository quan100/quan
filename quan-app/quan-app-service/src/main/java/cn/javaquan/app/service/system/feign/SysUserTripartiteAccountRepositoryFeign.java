package cn.javaquan.app.service.system.feign;

import cn.javaquan.app.service.system.feign.fallback.SysUserTripartiteAccountRepositoryFallback;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountDTO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
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
 * 用户第三方账户.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = SysUserTripartiteAccountRepositoryFallback.class)
public interface SysUserTripartiteAccountRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/sys/user/tripartite/account/page")
    Result<PageResult<SysUserTripartiteAccountDTO>> page(@SpringQueryMap SysUserTripartiteAccountQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/sys/user/tripartite/account/details")
    Result<SysUserTripartiteAccountDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/sys/user/tripartite/account/update")
    Result<Boolean> update(@RequestBody SysUserTripartiteAccountUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/sys/user/tripartite/account/save")
    Result<Boolean> save(@RequestBody SysUserTripartiteAccountAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/sys/user/tripartite/account/saveBatch")
    Result saveBatch(@RequestBody List<SysUserTripartiteAccountAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/sys/user/tripartite/account/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据邮箱查询绑定信息.
     * @param email 邮箱
     * @return 第三方账号绑定信息
     */
    @GetMapping("/core/sys/user/tripartite/account/getByEmail")
    Result<SysUserTripartiteAccountDTO> getByEmail(@RequestParam String email);

    /**
     * 根据查询条件查询绑定信息.
     * @param query 查询参数
     * @return 第三方账号绑定信息
     */
    @GetMapping("/core/sys/user/tripartite/account/getByTripartite")
    Result<SysUserTripartiteAccountDTO> getByTripartite(@SpringQueryMap SysUserTripartiteAccountQuery query);

}
