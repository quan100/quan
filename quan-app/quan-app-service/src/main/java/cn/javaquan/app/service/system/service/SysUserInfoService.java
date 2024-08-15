package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.module.system.SysUserInfoAddCommand;
import cn.javaquan.app.common.module.system.SysUserInfoDTO;
import cn.javaquan.app.common.module.system.SysUserInfoQuery;
import cn.javaquan.app.common.module.system.SysUserInfoUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.feign.SysUserInfoRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class SysUserInfoService {

    private final SysUserInfoRepositoryFeign sysUserInfoRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<SysUserInfoDTO>> page(SysUserInfoQuery query) {
        return sysUserInfoRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<SysUserInfoDTO> details(Long id) {
        return sysUserInfoRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(SysUserInfoUpdateCommand cmd) {
        return sysUserInfoRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(SysUserInfoAddCommand cmd) {
        return sysUserInfoRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<SysUserInfoAddCommand> cmds) {
        return sysUserInfoRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysUserInfoRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 根据用户ID查询信息.
     * @param userId 用户id
     * @return 用户信息
     */
    public Result<SysUserInfoDTO> getUserInfo(@RequestParam String userId) {
        return sysUserInfoRepositoryFeign.getUserInfo(userId);
    }

}
