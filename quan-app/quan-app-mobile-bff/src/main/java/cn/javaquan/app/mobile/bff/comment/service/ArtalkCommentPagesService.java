package cn.javaquan.app.mobile.bff.comment.service;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.OpenArtalkCommentPagesVO;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.mobile.bff.comment.convert.ArtalkCommentPagesAssembler;
import cn.javaquan.app.mobile.bff.comment.feign.ArtalkCommentPagesServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author wangquan
 * @version 1.0.0
 * @date 2020-02-12 19:50:38
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/friendly/link/")
public class ArtalkCommentPagesService {

    private final ArtalkCommentPagesServiceFeign artalkCommentPagesServiceFeign;

    /**
     * 查询页面统计数据
     *
     * @param keys
     * @return
     */
    public Result<List<OpenArtalkCommentPagesVO>> statistics(List<String> keys) {
        Result<List<ArtalkCommentPagesDTO>> result = artalkCommentPagesServiceFeign.statistics(keys);
        return RunUtil.doRun(result, () -> {
            return Result.success(ArtalkCommentPagesAssembler.INSTANCE.toOpenArtalkCommentPagesVOList(result.getData()));
        });
    }

}
