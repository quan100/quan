package cn.javaquan.app.service.friendly.feign.fallback;

import cn.javaquan.app.service.friendly.feign.FriendlyLinkRepositoryFeign;
import cn.javaquan.tools.notice.SystemNoticeException;
import cn.javaquan.app.common.module.friendly.FriendlyLinkAddCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkQuery;
import cn.javaquan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 友情链接
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class FriendlyLinkRepositoryFallback implements FallbackFactory<FriendlyLinkRepositoryFeign> {

    @Override
    public FriendlyLinkRepositoryFeign create(Throwable throwable) {
        return new FriendlyLinkRepositoryFeign() {
            @Override
            public Result page(FriendlyLinkQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(FriendlyLinkUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(FriendlyLinkAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<FriendlyLinkAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
