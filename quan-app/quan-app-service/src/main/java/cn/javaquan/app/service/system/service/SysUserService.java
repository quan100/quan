package cn.javaquan.app.service.system.service;

import cn.javaquan.app.common.module.system.SysUserAddCommand;
import cn.javaquan.app.common.module.system.SysUserUpdateCommand;
import cn.javaquan.app.common.module.system.SysUserVO;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.system.feign.SysUserRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class SysUserService {

    private final SysUserRepositoryFeign sysUserRepositoryFeign;

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<SysUserVO> details(Long id) {
        return sysUserRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(SysUserUpdateCommand cmd) {
        return sysUserRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(SysUserAddCommand cmd) {
        return sysUserRepositoryFeign.save(cmd);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return sysUserRepositoryFeign.deleteByIds(ids);
    }

}
