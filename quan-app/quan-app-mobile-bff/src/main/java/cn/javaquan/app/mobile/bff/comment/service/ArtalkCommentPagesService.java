package cn.javaquan.app.mobile.bff.comment.service;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.OpenArtalkCommentPagesVO;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.mobile.bff.comment.convert.ArtalkCommentPagesAssembler;
import cn.javaquan.app.mobile.bff.comment.feign.ArtalkCommentPagesServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * artalk 评论数据查询业务实现.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ArtalkCommentPagesService {

    private final ArtalkCommentPagesServiceFeign artalkCommentPagesServiceFeign;

    /**
     * 查询页面统计数据.
     * @param keys 页面唯一键
     * @return 统计数据
     */
    public Result<List<OpenArtalkCommentPagesVO>> statistics(List<String> keys) {
        Result<List<ArtalkCommentPagesDTO>> result = artalkCommentPagesServiceFeign.statistics(keys);
        return RunUtil.doRun(result, () -> {
            return Result
                .success(ArtalkCommentPagesAssembler.INSTANCE.toOpenArtalkCommentPagesVOList(result.getData()));
        });
    }

}
