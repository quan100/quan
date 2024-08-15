package cn.javaquan.app.service.system.feign.fallback;

import cn.javaquan.app.service.system.feign.SysRoleRepositoryFeign;
import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.system.SysRoleAddCommand;
import cn.javaquan.app.common.module.system.SysRoleDTO;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.module.system.SysRoleUpdateCommand;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class SysRoleRepositoryFallback implements FallbackFactory<SysRoleRepositoryFeign> {

    @Override
    public SysRoleRepositoryFeign create(Throwable throwable) {
        return new SysRoleRepositoryFeign() {
            @Override
            public Result page(SysRoleQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(SysRoleUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(SysRoleAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<SysRoleAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<SysRoleDTO> getRole(SysRoleQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<List<SysRoleDTO>> getRoles(SysRoleQuery query) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
