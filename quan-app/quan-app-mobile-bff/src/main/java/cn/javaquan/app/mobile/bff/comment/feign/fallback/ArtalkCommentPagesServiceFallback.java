package cn.javaquan.app.mobile.bff.comment.feign.fallback;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.mobile.bff.comment.feign.ArtalkCommentPagesServiceFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.notify.SystemNotifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
@Slf4j
@Component
public class ArtalkCommentPagesServiceFallback implements FallbackFactory<ArtalkCommentPagesServiceFeign> {

    @Override
    public ArtalkCommentPagesServiceFeign create(Throwable throwable) {
        return new ArtalkCommentPagesServiceFeign() {
            @Override
            public Result page(ArtalkCommentPagesQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(ArtalkCommentPagesUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(ArtalkCommentPagesAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<ArtalkCommentPagesAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<List<ArtalkCommentPagesDTO>> statistics(List<String> keys) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
