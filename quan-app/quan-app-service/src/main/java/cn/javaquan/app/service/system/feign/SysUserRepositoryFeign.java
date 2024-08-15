package cn.javaquan.app.service.system.feign;

import cn.javaquan.app.common.module.system.SysUserAddCommand;
import cn.javaquan.app.common.module.system.SysUserUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserVO;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}")
public interface SysUserRepositoryFeign {

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/sys/user")
    Result<SysUserVO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/sys/user")
    Result<Boolean> update(@RequestBody SysUserUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/sys/user")
    Result<Boolean> save(@RequestBody SysUserAddCommand cmd);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/sys/user")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
