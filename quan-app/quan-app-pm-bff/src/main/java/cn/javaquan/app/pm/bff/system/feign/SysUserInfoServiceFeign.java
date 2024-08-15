package cn.javaquan.app.pm.bff.system.feign;

import cn.javaquan.app.pm.bff.system.feign.fallback.SysUserInfoServiceFallback;
import cn.javaquan.app.common.module.system.SysUserInfoAddCommand;
import cn.javaquan.app.common.module.system.SysUserInfoDTO;
import cn.javaquan.app.common.module.system.SysUserInfoQuery;
import cn.javaquan.app.common.module.system.SysUserInfoUpdateCommand;
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
 * 用户信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = SysUserInfoServiceFallback.class)
public interface SysUserInfoServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/sys/user/info/page")
    Result<PageResult<SysUserInfoDTO>> page(@SpringQueryMap SysUserInfoQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/sys/user/info/details")
    Result<SysUserInfoDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/sys/user/info/update")
    Result<Boolean> update(@RequestBody SysUserInfoUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/sys/user/info/save")
    Result<Boolean> save(@RequestBody SysUserInfoAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/sys/user/info/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<SysUserInfoAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/sys/user/info/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据用户ID查询信息.
     * @param userId 用户id
     * @return 用户信息
     */
    @GetMapping("/service/sys/user/info/userInfo")
    Result<SysUserInfoDTO> getUserInfo(@RequestParam String userId);

}
