package cn.javaquan.app.pm.bff.system.feign.fallback;

import cn.javaquan.tools.notice.SystemNoticeException;
import cn.javaquan.app.common.module.system.SysUserRoleAddCommand;
import cn.javaquan.app.common.module.system.SysUserRoleDTO;
import cn.javaquan.app.common.module.system.SysUserRoleQuery;
import cn.javaquan.app.common.module.system.SysUserRoleUpdateCommand;
import cn.javaquan.app.pm.bff.system.feign.SysUserRoleServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户角色配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Slf4j
@Component
public class SysUserRoleServiceFallback implements FallbackFactory<SysUserRoleServiceFeign> {

    @Override
    public SysUserRoleServiceFeign create(Throwable throwable) {
        return new SysUserRoleServiceFeign() {
            @Override
            public Result page(SysUserRoleQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(SysUserRoleUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(SysUserRoleAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<SysUserRoleAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<List<SysUserRoleDTO>> getUserRole(String userId) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<Integer> getCount(List<Long> roleIds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<Boolean> delByRoleId(List<Long> roleIds) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}