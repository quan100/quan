package cn.javaquan.app.mobile.bff.friendly.feign.fallback;

import cn.javaquan.app.mobile.bff.friendly.feign.OpenFriendlyLinkServiceFeign;
import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.friendly.FriendlyLinkAddCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkQuery;
import cn.javaquan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 友情链接.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class OpenFriendlyLinkServiceFallback implements FallbackFactory<OpenFriendlyLinkServiceFeign> {

    @Override
    public OpenFriendlyLinkServiceFeign create(Throwable throwable) {
        return new OpenFriendlyLinkServiceFeign() {
            @Override
            public Result page(FriendlyLinkQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(FriendlyLinkUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(FriendlyLinkAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<FriendlyLinkAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
