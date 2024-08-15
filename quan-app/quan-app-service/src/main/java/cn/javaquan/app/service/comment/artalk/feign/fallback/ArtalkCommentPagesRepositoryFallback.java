package cn.javaquan.app.service.comment.artalk.feign.fallback;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.service.comment.artalk.feign.ArtalkCommentPagesRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.notify.SystemNotifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Artalk 评论数据接口用于创建服务降级的备选实现.
 * <p>
 * 当远程调用失败时，会触发该工厂创建备选实现来处理异常情况.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class ArtalkCommentPagesRepositoryFallback implements FallbackFactory<ArtalkCommentPagesRepositoryFeign> {

    @Override
    public ArtalkCommentPagesRepositoryFeign create(Throwable throwable) {
        return new ArtalkCommentPagesRepositoryFeign() {
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
                return null;
            }
        };
    }

}
