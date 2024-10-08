package cn.javaquan.app.pm.bff.system.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountDTO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountUpdateCommand;
import cn.javaquan.app.pm.bff.system.feign.SysUserTripartiteAccountServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户第三方账户.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class SysUserTripartiteAccountServiceFallback implements FallbackFactory<SysUserTripartiteAccountServiceFeign> {

    @Override
    public SysUserTripartiteAccountServiceFeign create(Throwable throwable) {
        return new SysUserTripartiteAccountServiceFeign() {
            @Override
            public Result page(SysUserTripartiteAccountQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(SysUserTripartiteAccountUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(SysUserTripartiteAccountAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<SysUserTripartiteAccountAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<SysUserTripartiteAccountDTO> getByEmail(String email) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<SysUserTripartiteAccountDTO> getByTripartite(SysUserTripartiteAccountQuery query) {
                throw new SystemNotifyException(throwable);
            }

        };
    }

}
